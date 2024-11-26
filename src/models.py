from sqlalchemy import Column, Integer, String, Boolean
from src.database import Base

class Task(Base):
    __tablename__ = 'tasks'

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String, index=True)
    is_done = Column(Boolean, default=False)
