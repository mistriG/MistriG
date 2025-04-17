from fastapi import FastAPI
from routes import auth_routes, mistri_routes, fields_routes

app = FastAPI()

app.include_router(auth_routes.router, tags=["Auth"])
app.include_router(mistri_routes.router, prefix="/mistri", tags=["Mistri"])
app.include_router(fields_routes.router, prefix="/fields", tags=["Fields"])
