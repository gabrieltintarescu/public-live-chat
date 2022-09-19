package com.gabrieltintarescu.ChatboxServer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/19/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String token;
    private String message;
}
