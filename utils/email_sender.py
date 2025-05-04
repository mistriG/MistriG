import os
from email.message import EmailMessage
import aiosmtplib
from dotenv import load_dotenv

load_dotenv()

EMAIL_USER = os.getenv("EMAIL_USER")
EMAIL_PASS = os.getenv("EMAIL_PASS")

async def send_otp_email(email: str, otp: str):
    message = EmailMessage()
    message["From"] = EMAIL_USER
    message["To"] = email
    message["Subject"] = "Your OTP Code"
    message.set_content(f"Your OTP is: {otp}. It expires in 10 minutes.")

    await aiosmtplib.send(
        message,
        hostname="smtp.gmail.com",
        port=587,
        start_tls=True,
        username=EMAIL_USER,
        password=EMAIL_PASS,
    )
