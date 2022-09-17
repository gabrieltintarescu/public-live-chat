package com.gabrieltintarescu.ChatboxServer.exception.errors;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
