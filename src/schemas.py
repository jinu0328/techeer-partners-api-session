from pydantic import BaseModel

class TaskCreate(BaseModel):
    title: str
    is_done: bool = False

class TaskUpdate(BaseModel):
    title: str = None
    is_done: bool = None

class TaskResponse(BaseModel):
    id: int
    title: str
    is_done: bool

    class Config:
        from_attributes = True
