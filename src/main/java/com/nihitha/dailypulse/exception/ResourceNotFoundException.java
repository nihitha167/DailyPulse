package com.nihitha.dailypulse.exception;

// custom exception to indicate the resource (like habit or DailyCheckin) was not found
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message){
        super(message);
    }
}
