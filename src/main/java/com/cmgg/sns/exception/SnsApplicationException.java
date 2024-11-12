package com.cmgg.sns.exception;

import lombok.AllArgsConstructor;

// TODO : implement
@AllArgsConstructor
public class SnsApplicationException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    @Override
    public String getMessage() {
        return String.format("%s, %s", errorCode, getMessage(), message);
    }
}
