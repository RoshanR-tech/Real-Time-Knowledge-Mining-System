from fastapi import FastAPI

app = FastAPI(
    title="Real-Time Knowledge Mining AI Service",
    description="AI service for real-time data ingestion and knowledge mining",
    version="1.0.0"
)


@app.get("/")
def home():
    return {
        "service": "Real-Time Knowledge Mining AI Service",
        "status": "running"
    }


@app.get("/health")
def health():
    return {
        "status": "healthy"
    }