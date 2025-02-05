package com.api.domain.exception;

import com.api.domain.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class AccountException extends Exception {

    public HttpStatus status;

    public ErrorTypeEnum errorType;

    public AccountException(String message, HttpStatus status, ErrorTypeEnum errorType) {
        super(message);

        this.status = status;
        this.errorType = errorType;
    }
}
