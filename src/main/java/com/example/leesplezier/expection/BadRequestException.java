package com.example.leesplezier.expection;

public class BadRequestException extends RuntimeException{

    //does it have added value?
    //private static final long serialVersionUID = 1L;
    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }

}
