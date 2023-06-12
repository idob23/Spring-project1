package com.javarush.springproject1.exception;

public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }
}
