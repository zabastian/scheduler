package org.example.scheduler.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorResponse {
    private final String errorCode;
    private final String errorMessage;

    public ApiErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
