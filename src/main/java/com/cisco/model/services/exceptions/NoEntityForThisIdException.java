package com.cisco.model.services.exceptions;

public class NoEntityForThisIdException extends RuntimeException{
    public NoEntityForThisIdException(String errorMessage) {super(errorMessage);}
}
