from fastapi import APIRouter, WebSocket, WebSocketDisconnect, UploadFile, File, HTTPException
from fastapi.staticfiles import StaticFiles
from database import db
from typing import Dict
import uuid
import os
from datetime import datetime
from schemas import MessagePayload

router = APIRouter()
UPLOAD_DIR = "media"

# Serve static media
if not os.path.exists(UPLOAD_DIR):
    os.makedirs(UPLOAD_DIR)

# WebSocket connections memory store
active_connections: Dict[str, WebSocket] = {}

# ------------------------------------
# Validate both sender and recipient in chat
# ------------------------------------
def validate_chat_participants(chat_id: str, sender_id: str, recipient_id: str):
    chat = db.chats.find_one({"_id": chat_id})
    if not chat:
        raise HTTPException(status_code=404, detail="Chat does not exist.")
    participants = chat.get("participants", [])
    if sender_id not in participants:
        raise HTTPException(status_code=403, detail="Sender is not a participant in this chat.")
    if recipient_id not in participants:
        raise HTTPException(status_code=403, detail="Recipient is not a participant in this chat.")
    return chat

# ------------------------------------
# WebSocket Chat Handler
# ------------------------------------
@router.websocket("/ws/{user_id}")
async def websocket_endpoint(websocket: WebSocket, user_id: str):
    await websocket.accept()
    active_connections[user_id] = websocket

    try:
        while True:
            data = await websocket.receive_json()

            chat_id = data.get("chat_id")
            recipient_id = data.get("recipient_id")

            chat = db.chats.find_one({"_id": chat_id})
            if not chat:
                await websocket.send_json({"error": "Chat does not exist"})
                continue
            if user_id not in chat["participants"]:
                await websocket.send_json({"error": "You are not a participant in this chat"})
                continue
            if recipient_id not in chat["participants"]:
                await websocket.send_json({"error": "Recipient is not a participant in this chat"})
                continue

            message = {
                "_id": str(uuid.uuid4()),
                "chat_id": chat_id,
                "sender_id": user_id,
                "type": data.get("type", "text"),
                "content": data.get("content", ""),
                "timestamp": datetime.utcnow()
            }

            db.messages.insert_one(message)

            if recipient_id in active_connections:
                await active_connections[recipient_id].send_json(message)

    except WebSocketDisconnect:
        active_connections.pop(user_id, None)

# ------------------------------------
# Start a New Chat Between Two Users
# ------------------------------------
@router.post("/start-chat/")
def start_chat(user1_id: str, user2_id: str):
    existing = db.chats.find_one({
        "participants": {"$all": [user1_id, user2_id]}
    })
    if existing:
        return {"chat_id": existing["_id"]}

    new_chat = {
        "_id": str(uuid.uuid4()),
        "participants": [user1_id, user2_id],
        "last_message": "",
        "last_updated": datetime.utcnow()
    }
    db.chats.insert_one(new_chat)
    return {"chat_id": new_chat["_id"]}

# ------------------------------------
# Send Message via REST API
# ------------------------------------
@router.post("/send-message/")
def send_message(data: MessagePayload):
    validate_chat_participants(data.chat_id, data.sender_id, data.recipient_id)

    message = {
        "_id": str(uuid.uuid4()),
        "chat_id": data.chat_id,
        "sender_id": data.sender_id,
        "type": data.type,
        "content": data.content,
        "timestamp": datetime.utcnow()
    }

    db.messages.insert_one(message)

    db.chats.update_one(
        {"_id": data.chat_id},
        {
            "$set": {
                "last_message": data.content,
                "last_updated": datetime.utcnow()
            }
        }
    )

    if data.recipient_id in active_connections:
        import asyncio
        asyncio.create_task(
            active_connections[data.recipient_id].send_json(message)
        )

    return {"status": "sent", "message": message}

# ------------------------------------
# List All Chats for a User
# ------------------------------------
@router.get("/chats/{user_id}")
def get_user_chats(user_id: str):
    chats = list(db.chats.find({"participants": user_id}).sort("last_updated", -1))
    enriched_chats = []

    for chat in chats:
        chat["_id"] = str(chat["_id"])
        chat["last_updated"] = chat["last_updated"].isoformat()

        # Identify the other participant
        participants = chat.get("participants", [])
        other_id = [pid for pid in participants if pid != user_id]
        other_user = {}

        if other_id:
            other_id = other_id[0]
            # Try fetching from both collections
            other_user = db.users.find_one({"user_id": other_id}) or db.mistris.find_one({"user_id": other_id})
            if other_user:
                other_user["_id"] = str(other_user["_id"])
                other_user.pop("password", None)  # Don't expose password

        chat["other_user"] = other_user
        enriched_chats.append(chat)

    return enriched_chats

# ------------------------------------
# List Messages by Chat ID
# ------------------------------------
@router.get("/messages/{chat_id}")
def get_messages(chat_id: str):
    messages = list(db.messages.find({"chat_id": chat_id}).sort("timestamp", 1))
    for m in messages:
        m["_id"] = str(m["_id"])
        m["timestamp"] = m["timestamp"].isoformat()
    return messages
