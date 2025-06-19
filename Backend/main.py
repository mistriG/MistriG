from fastapi import FastAPI
from routes import auth_routes, mistri_routes, admin_routes, user_routes, chat
from fastapi.staticfiles import StaticFiles
from fastapi.middleware.cors import CORSMiddleware
from fastapi.security import OAuth2PasswordBearer
from fastapi.openapi.models import OAuthFlows as OAuthFlowsModel
from fastapi.openapi.models import OAuthFlowPassword
from fastapi.security import OAuth2
from fastapi.openapi.utils import get_openapi


app = FastAPI(title="MistriG API",
    description="Got a mess? Call the best!",
    version="1.0.0")

# ✅ CORS Middleware (Security)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Replace with actual frontend URL in production
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(auth_routes.router, tags=["Auth"])

app.include_router(admin_routes.router,prefix="/admin", tags=["Admin"])

app.include_router(user_routes.router, prefix="/user", tags=["User"])

app.include_router(mistri_routes.router, prefix="/mistri", tags=["Mistri"])

app.include_router(chat.router, prefix="/chat", tags=["Chat"])
app.mount("/media", StaticFiles(directory="media"), name="media")

