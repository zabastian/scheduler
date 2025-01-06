package org.example.scheduler.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidateException extends RuntimeException {
    private String errorCode;
    private HttpStatus httpStatus;
    private String message;

    public ValidateException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.errorCode = "ERR001";
        this.httpStatus = httpStatus;
    }
}
