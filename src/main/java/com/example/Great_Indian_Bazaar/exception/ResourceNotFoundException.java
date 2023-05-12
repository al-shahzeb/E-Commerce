package com.example.Great_Indian_Bazaar.exception;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String resource){
        super(resource);
    }
}
