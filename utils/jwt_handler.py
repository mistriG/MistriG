import jwt
from datetime import datetime, timedelta

SECRET = "MISTRIG_SECRET"

def create_jwt(email: str):
    payload = {
        "sub": email,
        "exp": datetime.utcnow() + timedelta(days=1)
    }
    return jwt.encode(payload, SECRET, algorithm="HS256")

def decode_jwt(token: str):
    try:
        return jwt.decode(token, SECRET, algorithms=["HS256"])
    except jwt.ExpiredSignatureError:
        return None
