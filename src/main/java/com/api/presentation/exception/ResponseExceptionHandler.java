package com.api.presentation.exception;

import com.api.application.dto.errors.CustomErrorResponseDTO;
import com.api.domain.enums.ErrorTypeEnum;
import com.api.domain.exception.AccountException;
import com.api.domain.exception.AccountHolderException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(value = {AccountHolderException.class})
    private ResponseEntity<CustomErrorResponseDTO> handleAccountHolderException(AccountHolderException e) {
        CustomErrorResponseDTO customErrorResponse = new CustomErrorResponseDTO();

        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus(e.status.value());
        customErrorResponse.setType(e.errorType);

        return ResponseEntity.status(customErrorResponse.getStatus()).body(customErrorResponse);
    }

    @ExceptionHandler(value = {AccountException.class})
    private ResponseEntity<CustomErrorResponseDTO> handleAccountException(AccountException e) {
        CustomErrorResponseDTO customErrorResponse = new CustomErrorResponseDTO();

        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus(e.status.value());
        customErrorResponse.setType(e.errorType);

        return ResponseEntity.status(customErrorResponse.getStatus()).body(customErrorResponse);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    private ResponseEntity<CustomErrorResponseDTO> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        CustomErrorResponseDTO customErrorResponse = new CustomErrorResponseDTO();

        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setType(ErrorTypeEnum.ARGUMENT_NOT_VALID);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    private ResponseEntity<CustomErrorResponseDTO> handlerConstraintViolationException(ConstraintViolationException e) {
        CustomErrorResponseDTO customErrorResponse = new CustomErrorResponseDTO();

        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setType(ErrorTypeEnum.ARGUMENT_NOT_VALID);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customErrorResponse);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    private ResponseEntity<CustomErrorResponseDTO> internalErrorException(RuntimeException e) {
        CustomErrorResponseDTO customErrorResponse = new CustomErrorResponseDTO();

        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        customErrorResponse.setType(ErrorTypeEnum.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorResponse);
    }
}
