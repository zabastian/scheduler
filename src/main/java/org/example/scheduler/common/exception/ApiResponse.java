package org.example.scheduler.common.exception;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    // 속성
    private HttpStatus status;
    private String message;
    private T data;

    // 생성자
    private ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }



    // 기능
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }
}