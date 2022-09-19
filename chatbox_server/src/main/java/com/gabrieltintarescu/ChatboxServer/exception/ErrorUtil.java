package com.gabrieltintarescu.ChatboxServer.exception;

import org.springframework.web.context.request.WebRequest;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/17/2022
 */
public class ErrorUtil {
    public static String getPath(WebRequest request){
        return request.getDescription(false).substring(4);
    }
}
