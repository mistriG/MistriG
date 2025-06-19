import os
from dotenv import load_dotenv
from pymongo import MongoClient

load_dotenv()  # ✅ Load environment variables

MONGO_URL = os.getenv("MONGO_URL")
client = MongoClient(MONGO_URL)
db = client["mistrig"]

def get_collection(collection_name: str):
    return db[collection_name]

def get_db():
    return db
