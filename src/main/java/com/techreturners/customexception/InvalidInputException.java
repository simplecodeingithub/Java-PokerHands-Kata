package com.techreturners.customexception;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message){
        super(message);
    }
    @Override
    public String toString() {
        return getMessage(); // return only the error message
    }
}
