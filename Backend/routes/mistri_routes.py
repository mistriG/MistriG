from fastapi import APIRouter, HTTPException, Depends
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection
from bson import ObjectId
from models import Field
from schemas import FieldOut
from bson import ObjectId

router = APIRouter()

# @router.post("/add")
# def add_mistri(mistri: Mistri):
#     db.mistris.insert_one(mistri.dict())
#     return {"msg": "Mistri added"}

@router.get("/", response_model=list[FieldOut])
def get_fields():
    return list(db.fields.find({}, {"_id": 0}))


class LocationUpdate(BaseModel):
    email: str
    latitude: float
    longitude: float

@router.post("/mistri/update-location")
def update_mistri_location(data: LocationUpdate):
    print("📩 Incoming update for email:", data.email)
    mistri = db.mistris.find_one({"email": data.email})
    if not mistri:
        raise HTTPException(status_code=404, detail="Mistri not found")

    db.mistris.update_one(
        {"email": data.email},
        {"$set": {"location": {"lat": data.latitude, "lng": data.longitude}}}
    )
    return {"message": "Location updated successfully"}

class Availability(BaseModel):
    email: str
    available: bool


@router.post("/mistri/availability")
def update_availability(data: Availability):
    db.mistris.update_one(
        {"email": data.email},
        {"$set": {"available": data.available}}
    )
    return {"msg": "Availability updated"}

@router.get("/user/mistri/ratings/{email}")
def get_mistri_ratings(email: str):
    mistri = db.mistris.find_one({"email": email})
    if not mistri:
        raise HTTPException(status_code=404, detail="Mistri not found")

    mistri["_id"] = str(mistri["_id"])
    mistri.pop("password", None)
    return mistri

@router.get("/mistri/bookings/{email}")
def get_mistri_bookings(email: str):
    bookings = list(db.bookings.find({"mistri_email": email}))
    for b in bookings:
        b["_id"] = str(b["_id"])
    return bookings

@router.patch("/mistri/booking/{booking_id}/status")
def update_work_done_status(booking_id: str, work_done: bool):
    result = db.bookings.update_one(
        {"_id": ObjectId(booking_id)},
        {"$set": {"work_done": work_done}}
    )
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Booking not found")
    return {"msg": f"Booking work_done updated to {work_done}"}