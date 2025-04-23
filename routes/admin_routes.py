from fastapi import APIRouter, HTTPException
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection

router = APIRouter()

@router.get("/admin/users")
def get_all_users():
    users = db.users.find()
    return [
        {
            "_id": str(user.get("_id")),
            "name": user.get("name"),
            "email": user.get("email"),
            "role": user.get("role", "user")
        }
        for user in users
    ]


@router.get("/admin/bookings")
def get_all_bookings():
    bookings = db.bookings.find()
    return [
        {
            "_id": str(b.get("_id")),
            "user_email": b.get("user_email"),
            "mistri_email": b.get("mistri_email"),
            "date": b.get("date"),
            "time": b.get("time"),
            "location": b.get("location")
        }
        for b in bookings
    ]
