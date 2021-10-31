package com.cisco.model.services.exceptions;

public class InvalidParameterProvidedException extends RuntimeException {
    public InvalidParameterProvidedException(String errorMessage) {
        super(errorMessage);
    }
}
