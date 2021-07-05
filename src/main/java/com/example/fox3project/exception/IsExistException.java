package com.example.fox3project.exception;

public class IsExistException extends RuntimeException{
    private String value;
    private String method;


    public IsExistException(String value, String method) {
        this.value = value;
        this.method = method;
    }

    public IsExistException(String message, String value, String method) {
        super(message);
        this.value = value;
        this.method = method;
    }

    public IsExistException(String message, Throwable cause, String value, String method) {
        super(message, cause);
        this.value = value;
        this.method = method;
    }

    public IsExistException(Throwable cause, String value, String method) {
        super(cause);
        this.value = value;
        this.method = method;
    }

    public IsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String value, String method) {
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
