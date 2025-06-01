import jwt
from datetime import datetime, timedelta

SECRET = "MISTRIG_SECRET"
algorithm = "HS256"

def create_jwt(email: str, user_id: str, role: str):
    payload = {
        "sub": email,
        "user_id": user_id,
        "role": role,
        "exp": datetime.utcnow() + timedelta(days=1)
    }
    return jwt.encode(payload, SECRET, algorithm)

def decode_jwt(token: str):
    try:
        return jwt.decode(token, SECRET, [algorithm])
    except jwt.ExpiredSignatureError:
        return None
