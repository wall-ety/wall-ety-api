package com.hei.wallet.wallety.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ApiException{
    public AccessDeniedException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public AccessDeniedException(){
        super("Access denied", HttpStatus.FORBIDDEN);
    }
}
