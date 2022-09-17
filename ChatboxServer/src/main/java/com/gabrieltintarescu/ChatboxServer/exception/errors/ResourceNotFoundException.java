package com.gabrieltintarescu.ChatboxServer.exception.errors;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException() {
        super("Resource not found on server.");
    }

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
