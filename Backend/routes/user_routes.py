from fastapi import APIRouter, HTTPException, Depends
from bson import ObjectId
from pydantic import BaseModel
from database import db

router = APIRouter()

# ----------------- Search Mistris by Expertise -----------------
@router.get("/mistri/search")
def search_mistri(area_of_expertise: str = None):
    query = {}
    if area_of_expertise:
        query["field_of_expertise"] = {"$regex": area_of_expertise, "$options": "i"}

    results = list(db.mistris.find(query))
    for m in results:
        m["_id"] = str(m["_id"])
        m.pop("password", None)
    return results

# ----------------- All Mistri Locations -----------------
@router.get("/mistri/locations")
def get_all_mistri_locations():
    mistris = db.mistris.find({"location": {"$exists": True}})
    return [
        {**{k: v for k, v in m.items() if k != "password"}, "_id": str(m["_id"])}
        for m in mistris if isinstance(m.get("location"), dict)
    ]

# ----------------- Nearby Mistris -----------------
@router.get("/mistri/nearby")
def get_nearby(lat: float, lng: float, radius_km: float = 5):
    radius_deg = radius_km / 111.0
    mistris = db.mistris.find({
        "location.lat": {"$gte": lat - radius_deg, "$lte": lat + radius_deg},
        "location.lng": {"$gte": lng - radius_deg, "$lte": lng + radius_deg}
    })
    return [
        {**{k: v for k, v in m.items() if k != "password"}, "_id": str(m["_id"])}
        for m in mistris if isinstance(m.get("location"), dict)
    ]

# ----------------- Booking -----------------
class Location(BaseModel):
    lat: int
    lon: int

class Booking(BaseModel):
    user_email: str
    mistri_email: str
    date: str
    time: str
    location: Location
    work_done: bool = False
    
@router.post("/booking")
def book_mistri(data: Booking):
    db.bookings.insert_one(data.dict())
    return {"msg": "Mistri booked successfully"}

# ----------------- Rating System -----------------
@router.post("/mistri/rate/{mistri_email}")
def rate_mistri(mistri_email: str, new_rating: float):
    mistri = db.mistris.find_one({"email": mistri_email})
    if not mistri:
        raise HTTPException(status_code=404, detail="Mistri not found")

    ratings = mistri.get("ratings", {"average": 0.0, "count": 0})
    current_avg = ratings["average"]
    count = ratings["count"]

    updated_avg = ((current_avg * count) + new_rating) / (count + 1)

    db.mistris.update_one(
        {"email": mistri_email},
        {"$set": {"ratings": {"average": updated_avg, "count": count + 1}}}
    )

    return {"msg": "Rating updated", "new_average": updated_avg}

# ----------------- Get Mistri Ratings & Info -----------------
@router.get("/mistri/ratings/{email}")
def get_mistri_ratings(email: str):
    mistri = db.mistris.find_one({"email": email})
    if not mistri:
        raise HTTPException(status_code=404, detail="Mistri not found")

    mistri["_id"] = str(mistri["_id"])
    mistri.pop("password", None)
    return mistri

@router.get("/user/bookings/{email}")
def get_user_bookings(email: str):
    bookings = list(db.bookings.find({"user_email": email}))
    for b in bookings:
        b["_id"] = str(b["_id"])
    return bookings
