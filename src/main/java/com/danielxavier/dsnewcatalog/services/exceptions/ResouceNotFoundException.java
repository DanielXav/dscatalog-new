package com.danielxavier.dsnewcatalog.services.exceptions;

public class ResouceNotFoundException extends RuntimeException {
    public ResouceNotFoundException(String message){
        super(message);
    }
}
