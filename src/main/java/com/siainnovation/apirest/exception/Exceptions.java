package com.siainnovation.apirest.exception;

public class Exceptions extends Exception{
    public Exceptions(ExceptionType type) {
        super(ExceptionType.getMessageException(type));
    }
}
