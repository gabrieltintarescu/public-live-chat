package com.gabrieltintarescu.ChatboxServer.exception.errors;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public class UnauthorizedAccessException extends Exception{
    public UnauthorizedAccessException() {
        super("Access to the requested resource is denied.");
    }

    public UnauthorizedAccessException(String errorMessage) {
        super(errorMessage);
    }
}
