from pydantic import BaseModel, EmailStr
from typing import List
from pydantic import BaseModel, EmailStr

class MistriRegister(BaseModel):
    name: str
    email: EmailStr
    mobile: str
    field_of_expertise: str
    price: float
    description: str
    password: str


class UserRegister(BaseModel):
    name: str
    email: EmailStr
    password: str

class UserLogin(BaseModel):
    email: EmailStr
    password: str

class MistriOut(BaseModel):
    name: str
    email: EmailStr
    mobile: str
    expertise: List[str]
    price_per_day: float
    description: str


class FieldOut(BaseModel):
    name: str
