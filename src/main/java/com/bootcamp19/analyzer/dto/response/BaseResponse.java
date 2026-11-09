package com.bootcamp19.analyzer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private String status;
    private String message;
    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<T>("success","OK", data);
    }

    public static <T> BaseResponse<T> failed(T data, String message) {
        return new BaseResponse<T>("failed", message, null);
    }
}
