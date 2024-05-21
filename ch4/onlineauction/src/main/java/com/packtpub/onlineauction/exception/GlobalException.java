package com.packtpub.onlineauction.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionResponse> handleException(BusinessException e){
        BusinessExceptionResponse response = BusinessExceptionResponse.builder()
                .code(e.getCode())
                .message(e.getLocalizedMessage())
                .build();
        return ResponseEntity.ok(response);

    }
}
