package com.apps.backend.exceptions;

public class AuthorityNotFoundException extends RuntimeException{
    public AuthorityNotFoundException(String message){
        super(message);
    }
}
