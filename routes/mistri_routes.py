from fastapi import APIRouter, HTTPException
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection
from bson import ObjectId
from models import Field
from schemas import FieldOut

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

@router.get("/mistri/ratings/{email}")
def get_mistri_ratings(email: str):
    mistri = db.mistris.find_one({"email": email}, {"ratings": 1, "name": 1})
    if not mistri:
        raise HTTPException(status_code=404, detail="Mistri not found")

    ratings = mistri.get("ratings", [])
    avg_rating = round(sum(ratings) / len(ratings), 2) if ratings else None

    return {
        "name": mistri.get("name", ""),
        "email": email,
        "ratings": ratings,
        "average_rating": avg_rating,
        "total_ratings": len(ratings)
    }

