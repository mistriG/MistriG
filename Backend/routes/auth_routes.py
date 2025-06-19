from fastapi import APIRouter, HTTPException, Form
from database import db, get_collection
from models import User
from schemas import (
    OTPRequest, OTPVerify,
    LoginRequest, OTPMistriRequest,
    ResetPasswordRequest
    
)
from utils.id_generator import generate_next_id
from auth import hash_password, verify_password
from utils.jwt_handler import create_jwt
from utils.email_sender import send_otp_email
from datetime import datetime, timedelta
import random
from typing import List
from fastapi.security import OAuth2PasswordRequestForm
from fastapi import Depends


router = APIRouter()

# ------------------ USER REGISTRATION (OTP) ------------------

from fastapi import UploadFile, File, Form
from config.cloudinary_config import cloudinary

@router.post("/register")
async def register_user(
    name: str = Form(...),
    email: str = Form(...),
    password: str = Form(...),
    phone_number: str = Form(...),
    picture: UploadFile = File(...)
):
    if db.users.find_one({"email": email}):
        raise HTTPException(status_code=400, detail="Email already registered.")

    # ⬆ Upload image to Cloudinary
    result = cloudinary.uploader.upload(await picture.read(), folder="user_profiles", public_id=email)

    picture_url = result.get("secure_url")

    otp = str(random.randint(100000, 999999))
    await send_otp_email(email, otp)

    db.otps.delete_many({"email": email})
    db.otps.insert_one({
        "email": email,
        "otp": otp,
        "name": name,
        "password": hash_password(password),
        "phone_number": phone_number,
        "role": "user",
        "picture": picture_url,
        "created_at": datetime.utcnow(),
        "expires_at": datetime.utcnow() + timedelta(minutes=10)
    })

    return {"message": "OTP sent to your email for verification."}

# ------------------ MISTRI REGISTRATION (OTP) ------------------

@router.post("/register-mistri")
async def register_mistri(
    name: str = Form(...),
    email: str = Form(...),
    password: str = Form(...),
    phone: str = Form(...),
    field_of_expertise_raw: List[str] = Form(...),  # Accept comma-separated or repeated fields
    price: float = Form(...),
    description: str = Form(...),
    picture: UploadFile = File(...)
):
    if db.mistris.find_one({"email": email}):
        raise HTTPException(status_code=400, detail="Mistri already exists")

    result = cloudinary.uploader.upload(await picture.read(), folder="mistri_profiles", public_id=email)
    picture_url = result.get("secure_url")

    # ✅ Clean and split the input into a proper list
    field_of_expertise = []
    for item in field_of_expertise_raw:
        field_of_expertise.extend([i.strip() for i in item.split(",")])

    otp = str(random.randint(100000, 999999))
    await send_otp_email(email, otp)

    db.otps.delete_many({"email": email})
    db.otps.insert_one({
        "email": email,
        "otp": otp,
        "name": name,
        "password": hash_password(password),
        "role": "mistri",
        "field_of_expertise": field_of_expertise,  # ✅ Proper list
        "phone": phone,
        "price": price,
        "description": description,
        "picture": picture_url,
        "created_at": datetime.utcnow(),
        "expires_at": datetime.utcnow() + timedelta(minutes=10)
    })

    return {"message": "OTP sent to mistri email for verification"}

# ------------------ LOGIN ------------------

@router.post("/login")
async def login(data: LoginRequest):
    user = db.users.find_one({"email": data.email}) or db.mistris.find_one({"email": data.email})
    if not user:
        raise HTTPException(status_code=404, detail="User not found")

    if not verify_password(data.password, user["password"]):
        raise HTTPException(status_code=401, detail="Invalid credentials")

    role = user.get("role", "user")
    token = create_jwt(data.email, user["user_id"], role)

    # ✅ Common fields
    profile = {
        "access_token": token,
        "token_type": "bearer",
        "user_id": user.get("user_id"),
        "name": user.get("name"),
        "email": user.get("email"),
        "role": role,
        "picture": user.get("picture")
    }

    # ✅ Add role-specific fields
    if role == "user":
        profile["phone_number"] = user.get("phone_number")
    else:
        profile.update({
            "mobile": user.get("mobile"),
            "field_of_expertise": user.get("field_of_expertise"),
            "price": user.get("price"),
            "description": user.get("description"),
            "ratings": user.get("ratings", {"average": 0.0, "count": 0}),
            "location": user.get("location", {"lat": 0, "lng": 0}),
            "available": user.get("available", False)
        })

    return profile

# ------------------ ✅ SINGLE VERIFY OTP ROUTE ------------------

@router.post("/verify-otp")
def verify_otp(data: OTPVerify):
    record = db.otps.find_one({"email": data.email, "otp": data.otp})
    if not record:
        raise HTTPException(status_code=400, detail="Invalid OTP")
    if datetime.utcnow() > record["expires_at"]:
        raise HTTPException(status_code=400, detail="OTP expired")

    # ✅ Mistri Registration
    if record.get("role") == "mistri":
        new_id = generate_next_id("mistri", "mistris", "user_id")
        mistri_data = {
            "user_id": new_id,
            "name": record["name"],
            "email": record["email"],
            "password": record["password"],
            "role": "mistri",
            "field_of_expertise": record.get("field_of_expertise"),
            "mobile": record.get("phone"),
            "price": record.get("price"),
            "picture": record.get("picture"),
            "description": record.get("description"),
            "ratings": {
                "average": 0.0,
                "count": 0
            },
            "location": {
                "lat": 0,
                "lng": 0
            },
            "available": False
        }
        db.mistris.insert_one(mistri_data)
        db.otps.delete_one({"email": data.email})
        return {
            "message": "Mistri registered successfully after OTP verification",
            "user_id": new_id
        }

    # ✅ User Registration
    else:
        new_id = generate_next_id("user", "users", "user_id")
        user_data = {
            "user_id": new_id,
            "name": record["name"],
            "email": record["email"],
            "password": record["password"],
            "phone_number": record["phone_number"],
            "picture": record.get("picture"),
            "role": "user"
        }
        db.users.insert_one(user_data)
        db.otps.delete_one({"email": data.email})
        return {
            "message": "User registered successfully after OTP verification",
            "user_id": new_id
        }


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