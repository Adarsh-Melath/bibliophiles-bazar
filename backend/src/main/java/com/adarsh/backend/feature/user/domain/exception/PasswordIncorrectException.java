package com.adarsh.backend.feature.user.domain.exception;

public class PasswordIncorrectException  extends RuntimeException{
    public PasswordIncorrectException (String message){
        super(message);
    }
}
