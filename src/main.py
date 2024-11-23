from fastapi import FastAPI
from src.routers import tasks
from src.database import Base, engine

# 데이터베이스 초기화
Base.metadata.create_all(bind=engine)

# FastAPI 앱 생성
app = FastAPI(
    title="Todo List API",
    description="FastAPI를 사용한 Todo List API",
    version="1.0.0"
)

# Task 라우터 추가
app.include_router(tasks.router)
