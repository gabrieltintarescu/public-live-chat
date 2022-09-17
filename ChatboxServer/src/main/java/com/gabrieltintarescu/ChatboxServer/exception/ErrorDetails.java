package com.gabrieltintarescu.ChatboxServer.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@Data
@Builder
@AllArgsConstructor
public class ErrorDetails {
    /* Code mapping:
    0 - General Error
    1 - Unauthorized
    2 - Token Expired
    */

    private Date timestamp;
    private int code;
    private String message;
    private String details;
    private String path;
}
