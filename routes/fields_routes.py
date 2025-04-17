from fastapi import APIRouter
from database import db
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
