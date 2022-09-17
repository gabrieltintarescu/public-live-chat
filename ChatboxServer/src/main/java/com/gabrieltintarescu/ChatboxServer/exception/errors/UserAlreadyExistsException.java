package com.gabrieltintarescu.ChatboxServer.exception.errors;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("An user with this username/email already exists.");
    }

    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
