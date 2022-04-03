package com.kata_3_1_2.PP.exception_handling;

public class NoSuchUserException extends RuntimeException{

    public NoSuchUserException(String message) {
        super(message);
    }
}
