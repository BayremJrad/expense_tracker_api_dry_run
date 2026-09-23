from dotenv import load_dotenv
load_dotenv()

from app import app
app.run(host="0.0.0.0", port=4510, debug=True)
