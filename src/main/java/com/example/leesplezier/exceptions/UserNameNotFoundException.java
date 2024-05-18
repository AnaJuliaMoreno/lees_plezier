package com.example.leesplezier.exceptions;

public class UserNameNotFoundException extends RuntimeException{
    public UserNameNotFoundException(){
        super();
    }
    public UserNameNotFoundException(String message){
        super(message);
    }
}
