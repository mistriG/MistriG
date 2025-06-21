from fastapi import FastAPI, WebSocket, WebSocketDisconnect
from typing import Dict

app = FastAPI()
connections: Dict[str, WebSocket] = {}

@app.websocket("/ws/{user_id}")
async def websocket_endpoint(websocket: WebSocket, user_id: str):
    await websocket.accept()
    connections[user_id] = websocket
    try:
        while True:
            data = await websocket.receive_json()
            target_id = data.get("target_id")
            if target_id in connections:
                await connections[target_id].send_json(data)
    except WebSocketDisconnect:
        connections.pop(user_id, None)