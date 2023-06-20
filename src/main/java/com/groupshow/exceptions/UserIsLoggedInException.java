package com.groupshow.exceptions;

public class UserIsLoggedInException extends Exception {
    public UserIsLoggedInException() {
        super("User is already logged in.");
    }
}
