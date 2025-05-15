package com.intellij.core.exception;

import com.intellij.core.enums.ResponseCode;

public class HitechException extends Exception{

    private ResponseCode errorCode;
    private String[] fields;
    private Exception exception;

    public HitechException() {
        super("Failed to do operation");
        this.errorCode = ResponseCode.INTERNAL_ERROR;
        this.exception = new RuntimeException();
    }

    public HitechException(ResponseCode code, String message, String... fields) {
        super(message);
        this.errorCode = code;
        this.fields = fields;
    }

    public HitechException(Exception exception) {
        super(exception.getLocalizedMessage());
        this.errorCode = ResponseCode.INTERNAL_ERROR;
        this.exception = exception;
    }
}
