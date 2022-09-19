package com.gabrieltintarescu.ChatboxServer.security.util;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/17/2022
 */
public class JwtUtil {
    public static final String STRING_BEARER = "Bearer ";
    public static final int ACCESS_TOKEN_EXP = 30; // Time in minutes for access token to expire
    public static final int REFRESH_TOKEN_EXP = 12; // Time in hours for access token to expire
    public static int BearerLength() {
        return STRING_BEARER.length();
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256("secret-$2a$10$Kx.J7N".getBytes());
    }


}
