import os
import sys

# Ensure the src directory is on the path so sibling imports work
sys.path.insert(0, os.path.dirname(__file__))

from flask import Flask, jsonify
from routes.categories import bp as categories_bp
from routes.expenses import bp as expenses_bp
from routes.receipts import bp as receipts_bp
from routes.claims import bp as claims_bp
from routes.claim_actions import bp as claim_actions_bp


def create_app() -> Flask:
    app = Flask(__name__)

    # Register blueprints
    app.register_blueprint(categories_bp)
    app.register_blueprint(expenses_bp)
    app.register_blueprint(receipts_bp)
    app.register_blueprint(claims_bp)
    app.register_blueprint(claim_actions_bp)

    # Global error handlers
    @app.errorhandler(404)
    def not_found(e):
        return jsonify({"error": {"code": "NOT_FOUND", "message": "The requested resource was not found"}}), 404

    @app.errorhandler(405)
    def method_not_allowed(e):
        return jsonify({"error": {"code": "METHOD_NOT_ALLOWED", "message": "Method not allowed"}}), 405

    @app.errorhandler(500)
    def internal_error(e):
        return jsonify({"error": {"code": "INTERNAL_ERROR", "message": "An unexpected error occurred"}}), 500

    return app


app = create_app()
