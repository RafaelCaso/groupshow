package com.groupshow.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String identifier) {
        super("A user with this " + identifier + " was not found.");
    }
}
