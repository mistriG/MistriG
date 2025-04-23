from fastapi import APIRouter, HTTPException
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection
from bson import ObjectId

router = APIRouter()

# @router.post("/add")
# def add_mistri(mistri: Mistri):
#     db.mistris.insert_one(mistri.dict())
#     return {"msg": "Mistri added"}

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
    
    
@router.get("/mistri/search")
def search_mistri(area: str = None, expertise: str = None):
    query = {}
    if area:
        query["address"] = {"$regex": area, "$options": "i"}
    if expertise:
        query["field_of_expertise"] = {"$regex": expertise, "$options": "i"}
    
    results = list(db.mistris.find(query, {"name": 1, "mobile": 1, "field_of_expertise": 1, "price": 1}))
    
    # Convert ObjectId to string
    for m in results:
        m["_id"] = str(m["_id"])
    
    return results
class Rating(BaseModel):
    email: str
    rating: int

@router.post("/mistri/rate")
def rate_mistri(data: Rating):
    db.mistris.update_one(
        {"email": data.email},
        {"$push": {"ratings": data.rating}}
    )
    return {"msg": "Rating submitted"}

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

class Booking(BaseModel):
    user_email: str
    mistri_email: str
    date: str
    time: str
    location: str

@router.post("/booking")
def book_mistri(data: Booking):
    db.bookings.insert_one(data.dict())
    return {"msg": "Mistri booked successfully"}

@router.get("/mistri/nearby")
def get_nearby(lat: float, lng: float, radius_km: float = 5):
    # Approximate conversion: 1 degree latitude/longitude ~ 111 km
    radius_deg = radius_km / 111.0

    mistris = db.mistris.find({
        "location.lat": {"$gte": lat - radius_deg, "$lte": lat + radius_deg},
        "location.lng": {"$gte": lng - radius_deg, "$lte": lng + radius_deg}
    }, {"name": 1, "location": 1})

    # Convert ObjectId and return proper response
    return [
        {
            "_id": str(m.get("_id")),
            "name": m.get("name", ""),
            "lat": m["location"]["lat"],
            "lng": m["location"]["lng"]
        }
        for m in mistris if "location" in m
    ]