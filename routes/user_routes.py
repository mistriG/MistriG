from fastapi import APIRouter, HTTPException
from database import db
from models import Mistri
from schemas import MistriOut
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel
from database import db  # MongoDB connection
from bson import ObjectId

router = APIRouter()
  
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

