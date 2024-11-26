from sqlalchemy import create_engine
from sqlalchemy.orm import declarative_base, sessionmaker  # 변경된 경로로 import

# 데이터베이스 URL
DATABASE_URL = "sqlite:///./test.db"

# 데이터베이스 엔진
engine = create_engine(DATABASE_URL, connect_args={"check_same_thread": False})

# 세션 로컬 생성
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# Declarative Base 클래스 생성
Base = declarative_base()
