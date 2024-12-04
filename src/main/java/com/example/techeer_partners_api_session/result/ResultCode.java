package com.example.techeer_partners_api_session.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    TASK_CREATE_SUCCESS("SUCCESS", "할 일이 생성되었습니다."),
    TASK_UPDATE_SUCCESS("SUCCESS", "할 일이 수정되었습니다."),
    TASK_DELETE_SUCCESS("SUCCESS","할 일이 삭제되었습니다." ),
    TASK_GET_COMPLETE_SUCCESS("SUCCESS","완료 된 일이 조회되었습니다." ),
    TASK_GET_INCOMPLETE_SUCCESS("SUCCESS", "미완료 된 일이 조회되었습니다."),
    TASK_GET_SUCCESS("SUCCESS","모든 할 일이 조회되었습니다." );

    private final String status;
    private final String message;
}