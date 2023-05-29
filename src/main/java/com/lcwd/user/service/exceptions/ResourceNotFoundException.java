package com.lcwd.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    // extra properties you want to manage
    public ResourceNotFoundException() {
        super("Sorry Resource not found on server.....>");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
