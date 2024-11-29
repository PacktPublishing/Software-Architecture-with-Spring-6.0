package com.packtpub.authenticationservices.internal.exceptions;

public class BusinessException extends RuntimeException{

    private String code;

    private String message;

    public BusinessException(String code, String message) {}

    public BusinessException() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
