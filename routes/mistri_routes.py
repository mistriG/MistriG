from fastapi import APIRouter, HTTPException
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection

router = APIRouter()

@router.post("/add")
def add_mistri(mistri: Mistri):
    db.mistris.insert_one(mistri.dict())
    return {"msg": "Mistri added"}

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



@router.get("/mistri/locations")
def get_all_mistri_locations():
    mistris = db.mistris.find({"location": {"$exists": True}}, {"name": 1, "location": 1})
    return [
        {
            "name": m.get("name", ""),
            "lat": m["location"]["lat"],
            "lng": m["location"]["lng"]
        }
        for m in mistris
        if "location" in m and isinstance(m["location"], dict)
    ]
