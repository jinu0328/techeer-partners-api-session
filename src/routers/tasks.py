from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from src.database import SessionLocal
from src.schemas import TaskCreate, TaskUpdate, TaskResponse
from src import crud


router = APIRouter(
    prefix="/tasks",
    tags=["Tasks"]
)

# DB 세션 의존성
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# 할 일 생성
# 할 일 생성
@router.post("", response_model=TaskResponse, status_code=201)
def create_task(task: TaskCreate, db: Session = Depends(get_db)):
    new_task = crud.create_task(db, task)
    return new_task


# 전체 할 일 조회
@router.get("", response_model=list[TaskResponse])
def read_tasks(db: Session = Depends(get_db)):
    return crud.get_tasks(db)

# 완료된 일 조회
@router.get("/completed", response_model=list[TaskResponse])
def read_completed_tasks(db: Session = Depends(get_db)):
    return crud.get_tasks_by_status(db, is_done=True)

# 미완료된 일 조회
@router.get("/incomplete", response_model=list[TaskResponse])
def read_incomplete_tasks(db: Session = Depends(get_db)):
    return crud.get_tasks_by_status(db, is_done=False)

# 할 일 수정
@router.patch("/{id}", response_model=TaskResponse)
def update_task(id: int, task_update: TaskUpdate, db: Session = Depends(get_db)):
    task = crud.update_task(db, id, task_update)
    if not task:
        raise HTTPException(status_code=404, detail="Task not found")
    return {"status": "success", "message": "할 일이 수정되었습니다."}

# 할 일 삭제
@router.delete("/{id}")
def delete_task(id: int, db: Session = Depends(get_db)):
    task = crud.delete_task(db, id)
    if not task:
        raise HTTPException(status_code=404, detail="Task not found")
    return {"status": "success", "message": "할 일이 삭제되었습니다."}
