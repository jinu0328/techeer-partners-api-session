from sqlalchemy.orm import Session
from src.models import Task
from src.schemas import TaskCreate, TaskUpdate

def get_tasks(db: Session):
    return db.query(Task).all()

def get_tasks_by_status(db: Session, is_done: bool):
    return db.query(Task).filter(Task.is_done == is_done).all()

def get_task(db: Session, task_id: int):
    return db.query(Task).filter(Task.id == task_id).first()

def create_task(db: Session, task: TaskCreate):
    db_task = Task(**task.dict())
    db.add(db_task)
    db.commit()
    db.refresh(db_task)
    return db_task


def update_task(db: Session, task_id: int, task_update: TaskUpdate):
    db_task = get_task(db, task_id)
    if db_task:
        if task_update.title is not None:
            db_task.title = task_update.title
        if task_update.is_done is not None:
            db_task.is_done = task_update.is_done
        db.commit()
        db.refresh(db_task)
    return db_task

def delete_task(db: Session, task_id: int):
    db_task = get_task(db, task_id)
    if db_task:
        db.delete(db_task)
        db.commit()
    return db_task
