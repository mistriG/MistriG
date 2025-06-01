from pydantic import BaseModel, EmailStr
from typing import List
from pydantic import BaseModel, EmailStr
from pydantic import HttpUrl
from typing import Optional, Dict

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
    
class Location(BaseModel):
    lat: float
    lng: float

class MistriOut(BaseModel):
    _id: Optional[str]
    user_id: str
    name: str
    email: str
    mobile: str
    field_of_expertise: List[str]
    price: float
    description: str
    picture: Optional[HttpUrl]
    role: Optional[str]
    ratings: Dict[str, float]
    location: Optional[Location] = None
    available: Optional[bool] = False
    
    
class UserOut(BaseModel):
    _id: Optional[str]
    user_id: str
    name: str
    email: str
    phone_number: str
    picture: Optional[HttpUrl]
    role: Optional[str]


class FieldOut(BaseModel):
    name: str


from pydantic import BaseModel, EmailStr

class OTPRequest(BaseModel):
    email: EmailStr
    name: str
    password: str
    phone_number: str
    picture: Optional[HttpUrl] = None
    
class OTPVerify(BaseModel):
    email: EmailStr
    otp: str


class LoginRequest(BaseModel):
    email: EmailStr
    password: str

class OTPLoginVerify(BaseModel):
    email: EmailStr
    otp: str


class OTPMistriRequest(BaseModel):
    name: str
    email: EmailStr
    password: str
    field_of_expertise: List[str]
    price: str
    phone: str
    description: str
    picture: Optional[HttpUrl] = None

class OTPMistriVerify(BaseModel):
    email: EmailStr
    otp: str


class ForgotPasswordRequest(BaseModel):
    email: EmailStr

class ResetPasswordRequest(BaseModel):
    email: EmailStr
    new_password: str
    
class MessagePayload(BaseModel):
    chat_id: str
    sender_id: str
    recipient_id: str
    content: str
    type: str = "text" 
