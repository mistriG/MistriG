from fastapi import APIRouter, HTTPException
from database import db
from models import User
from schemas import UserRegister, UserLogin
from utils.jwt_handler import create_jwt
from auth import hash_password, verify_password
from fastapi import APIRouter, HTTPException, Depends
from pydantic import EmailStr, BaseModel
from database import get_collection
from models import User
from database import get_db
from schemas import MistriRegister
from bson.objectid import ObjectId

router = APIRouter()

@router.post("/register")
def register_user(user: UserRegister):
    if db.users.find_one({"email": user.email}):
        raise HTTPException(status_code=400, detail="Email already exists")
    
    user_dict = {
        "name": user.name,
        "email": user.email,
        "password": hash_password(user.password),
        "role": "user"  # ✅ Add role here
    }

    db.users.insert_one(user_dict)
    return {"msg": "User registered successfully"}

@router.post("/register-mistri")
def register_mistri(mistri: MistriRegister):
    db = get_db()
    if db.mistris.find_one({"email": mistri.email}):
        raise HTTPException(status_code=400, detail="Mistri with this email already exists")

    mistri_dict = mistri.dict()
    mistri_dict["password"] = hash_password(mistri.password)
    mistri_dict["role"] = "mistri"  # ✅ Add role here

    db.mistris.insert_one(mistri_dict)
    return {"message": "Mistri registered successfully"}

@router.post("/login")
def login(user: UserLogin):
    # Try to find user in "users" collection
    record = db.users.find_one({"email": user.email})
    
    # If not found, try in "mistris" collection
    if not record:
        record = db.mistris.find_one({"email": user.email})
        if not record:
            raise HTTPException(status_code=404, detail="User not found")

    # Password verification
    if not verify_password(user.password, record["password"]):
        raise HTTPException(status_code=401, detail="Invalid password")

    # JWT generation
    token = create_jwt(user.email)
    return {
        "access_token": token,
        "token_type": "bearer"
    }


class ForgotPasswordRequest(BaseModel):
    email: EmailStr

class ResetPasswordRequest(BaseModel):
    email: EmailStr
    new_password: str

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