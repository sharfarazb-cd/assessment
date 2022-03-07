package com.clouddestinations.engg.assessment.exceptions;

public class ManagerDoesNotExistException extends RuntimeException{

    public ManagerDoesNotExistException() {
        super();
    }

    public ManagerDoesNotExistException(String message) {
        super(message);
    }
    
}
