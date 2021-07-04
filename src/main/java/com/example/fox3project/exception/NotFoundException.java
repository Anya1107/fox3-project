package com.example.fox3project.exception;

public class NotFoundException extends RuntimeException{
    private String value;
    public String method;


    public NotFoundException(String value, String method) {
        this.value = value;
        this.method = method;
    }

    public NotFoundException(String message, String value, String method) {
        super(message);
        this.value = value;
        this.method = method;
    }

    public NotFoundException(String message, Throwable cause, String value, String method) {
        super(message, cause);
        this.value = value;
        this.method = method;
    }

    public NotFoundException(Throwable cause, String value, String method) {
        super(cause);
        this.value = value;
        this.method = method;
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String value, String method) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.value = value;
        this.method = method;
    }

    public String getValue() {
        return value;
    }

    public String getMethod() {
        return method;
    }
}
