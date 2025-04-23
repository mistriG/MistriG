from fastapi import APIRouter, HTTPException
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection
from models import Field
from schemas import FieldOut

router = APIRouter()

@router.post("/add")
def add_field(field: Field):
    db.fields.insert_one(field.dict())
    return {"msg": "Field added"}

@router.get("/", response_model=list[FieldOut])
def get_fields():
    return list(db.fields.find({}, {"_id": 0}))

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

@router.get("/mistri/all", response_model=list[MistriOut])
def get_all_mistris():
    mistris = list(db.mistris.find())
    return [
        {
            "name": m["name"],
            "email": m["email"],
            "mobile": m["mobile"],
            "expertise":[m["field_of_expertise"]], # string like "Cleaning"
            "price_per_day": m["price"],
            "description": m["description"]
        }
        for m in mistris
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
