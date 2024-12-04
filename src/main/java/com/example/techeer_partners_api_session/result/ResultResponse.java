package com.example.techeer_partners_api_session.result;

import lombok.Getter;

@Getter
public class ResultResponse<T> {

    private String status;
    private String message;
    private T data;

    public static <T> ResultResponse<T> of(ResultCode resultCode, T data) {
        return new ResultResponse<>(resultCode, data);
    }
    public ResultResponse(ResultCode resultCode, T data) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
        this.data = data;
    }

}
