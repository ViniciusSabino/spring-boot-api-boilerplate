package com.api.domain.exception;

import com.api.domain.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class AccountHolderException extends Exception {

    public HttpStatus status;

    public ErrorTypeEnum errorType;

    public AccountHolderException(String message, HttpStatus status, ErrorTypeEnum errorType) {
        super(message);

        this.status = status;
        this.errorType = errorType;
    }
}
