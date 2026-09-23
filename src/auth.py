import os
import jwt
from functools import wraps
from flask import request, g
from datetime import datetime, timezone

JWT_SECRET = os.environ.get("JWT_SECRET", "dev-secret")
JWT_ALGORITHM = "HS256"


def decode_token(token: str) -> dict:
    return jwt.decode(token, JWT_SECRET, algorithms=[JWT_ALGORITHM])


def get_token_from_header():
    auth = request.headers.get("Authorization", "")
    if not auth.startswith("Bearer "):
        return None
    return auth[len("Bearer "):]


def require_auth(*roles):
    """Decorator factory. Pass allowed roles, or no args to allow any authenticated user."""
    def decorator(fn):
        @wraps(fn)
        def wrapper(*args, **kwargs):
            token = get_token_from_header()
            if not token:
                return {"error": {"code": "UNAUTHORIZED", "message": "Missing or invalid Authorization header"}}, 401
            try:
                payload = decode_token(token)
            except jwt.ExpiredSignatureError:
                return {"error": {"code": "TOKEN_EXPIRED", "message": "Token has expired"}}, 401
            except jwt.InvalidTokenError as e:
                return {"error": {"code": "INVALID_TOKEN", "message": str(e)}}, 401

            g.user_id = payload.get("sub")
            g.role = payload.get("role")

            if roles and g.role not in roles:
                return {"error": {"code": "FORBIDDEN", "message": f"Role '{g.role}' is not allowed to perform this action"}}, 403

            return fn(*args, **kwargs)
        return wrapper
    return decorator
