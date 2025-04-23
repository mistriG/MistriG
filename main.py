from fastapi import FastAPI
from routes import auth_routes, mistri_routes, admin_routes, user_routes

app = FastAPI()

app.include_router(auth_routes.router, tags=["Auth"])

app.include_router(admin_routes.router,prefix="/admin", tags=["Admin"])

app.include_router(user_routes.router, prefix="/user", tags=["User"])

app.include_router(mistri_routes.router, prefix="/mistri", tags=["Mistri"])

