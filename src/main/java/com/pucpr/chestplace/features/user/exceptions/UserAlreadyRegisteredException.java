package com.pucpr.chestplace.features.user.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    
    public UserAlreadyRegisteredException(String email) {
        super("User with email '"+ email +"' already registered.");
    }

}
