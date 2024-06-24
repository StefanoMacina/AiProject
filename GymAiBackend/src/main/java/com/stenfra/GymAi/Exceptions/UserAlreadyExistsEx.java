package com.stenfra.GymAi.Exceptions;

public class UserAlreadyExistsEx extends Exception{

    public UserAlreadyExistsEx(String message) {
        super(message);
    }

    public UserAlreadyExistsEx(String message, Throwable cause) {
        super(message, cause);
    }
}
