from fastapi import APIRouter, HTTPException
from database import db, get_db, get_collection
from models import User
from schemas import (
    UserRegister, UserLogin,
    OTPRequest, OTPVerify,
    OTPLoginRequest, OTPMistriRequest,
    ResetPasswordRequest
    
)
from auth import hash_password, verify_password
from utils.jwt_handler import create_jwt
from utils.email_sender import send_otp_email
from datetime import datetime, timedelta
import random

router = APIRouter()

# ------------------ USER REGISTRATION (OTP) ------------------

@router.post("/register")
async def register_user(data: OTPRequest):
    if db.users.find_one({"email": data.email}):
        raise HTTPException(status_code=400, detail="Email already registered.")

    otp = str(random.randint(100000, 999999))
    await send_otp_email(data.email, otp)

    db.otps.delete_many({"email": data.email})
    db.otps.insert_one({
        "email": data.email,
        "otp": otp,
        "name": data.name,
        "password": hash_password(data.password),
        "role": "user",
        "created_at": datetime.utcnow(),
        "expires_at": datetime.utcnow() + timedelta(minutes=10)
    })

    return {"message": "OTP sent to your email for verification."}


# ------------------ MISTRI REGISTRATION (OTP) ------------------

@router.post("/register-mistri")
async def register_mistri(data: OTPMistriRequest):
    if db.mistris.find_one({"email": data.email}):
        raise HTTPException(status_code=400, detail="Mistri already exists")

    otp = str(random.randint(100000, 999999))
    await send_otp_email(data.email, otp)

    db.otps.delete_many({"email": data.email})
    db.otps.insert_one({
        "email": data.email,
        "otp": otp,
        "name": data.name,
        "password": hash_password(data.password),
        "role": "mistri",
        "skill": data.skill,
        "phone": data.phone,
        "created_at": datetime.utcnow(),
        "expires_at": datetime.utcnow() + timedelta(minutes=10)
    })

    return {"message": "OTP sent to mistri email for verification"}


# ------------------ LOGIN VIA OTP ------------------

@router.post("/login")
async def login(data: OTPLoginRequest):
    user = db.users.find_one({"email": data.email}) or db.mistris.find_one({"email": data.email})
    if not user:
        raise HTTPException(status_code=404, detail="User not found")
    
    if not verify_password(data.password, user["password"]):
        raise HTTPException(status_code=401, detail="Invalid password")


    otp = str(random.randint(100000, 999999))
    await send_otp_email(data.email, otp)

    db.otps.delete_many({"email": data.email})
    db.otps.insert_one({
        "email": data.email,
        "otp": otp,
        "login": True,
        "created_at": datetime.utcnow(),
        "expires_at": datetime.utcnow() + timedelta(minutes=10)
    })

    return {"message": "OTP sent to your email for login verification"}


# ------------------ ✅ SINGLE VERIFY OTP ROUTE ------------------

@router.post("/verify-otp")
def verify_otp(data: OTPVerify):
    record = db.otps.find_one({"email": data.email, "otp": data.otp})
    if not record:
        raise HTTPException(status_code=400, detail="Invalid OTP")
    if datetime.utcnow() > record["expires_at"]:
        raise HTTPException(status_code=400, detail="OTP expired")

    # Login verification
    if record.get("login"):
        user = db.users.find_one({"email": data.email}) or db.mistris.find_one({"email": data.email})
        if not user:
            raise HTTPException(status_code=404, detail="User not found")

        token = create_jwt(user["email"])
        db.otps.delete_one({"email": data.email})
        return {
            "access_token": token,
            "token_type": "bearer",
            "email": user["email"],
            "role": user.get("role", "user")
        }

    # Mistri Registration
    elif record.get("role") == "mistri":
        mistri_data = {
            "name": record["name"],
            "email": record["email"],
            "password": record["password"],
            "role": "mistri",
            "field_of_expertise": record.get("skill"),
            "mobile": record.get("phone"),
            "price": 0.0,  # You can customize this
            "description": "Added via OTP"
        }
        db.mistris.insert_one(mistri_data)
        db.otps.delete_one({"email": data.email})
        return {"message": "Mistri registered successfully after OTP verification"}

    # User Registration
    else:
        user_data = {
            "name": record["name"],
            "email": record["email"],
            "password": record["password"],
            "role": "user"
        }
        db.users.insert_one(user_data)
        db.otps.delete_one({"email": data.email})
        return {"message": "User registered successfully after OTP verification"}


@router.post("/reset_password")
async def reset_password(data: ResetPasswordRequest):
    users = get_collection("users")
    hashed_pw = hash_password(data.new_password)
    result = users.update_one(
        {"email": data.email},
        {"$set": {"password": hashed_pw}}
    )
    if result.modified_count == 0:
        raise HTTPException(status_code=400, detail="Password reset failed")
    return {"message": "Password has been reset successfully"}

