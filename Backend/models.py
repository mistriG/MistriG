from pydantic import BaseModel, EmailStr
from typing import List, Optional

class User(BaseModel):
    name: str
    email: EmailStr
    password: str

class Mistri(BaseModel):
    name: str
    mobile: str
    price_per_day: float
    expertise: List[str]

class Field(BaseModel):
    name: str
