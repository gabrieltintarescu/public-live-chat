package com.gabrieltintarescu.ChatboxServer.security.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/17/2022
 */
@Slf4j
public class SecurityUtil {
    public static List<String> allowedURLS = List.of(
            "/api/v1/login",
            "/api/v1/user/register",
            "/api/v1/refresh-token"
    );
    public static boolean isUrlAccepted(String url){
        for (int i = 0; i < allowedURLS.size(); i++) {
            if(url.equals(allowedURLS.get(i))){
                return true;
            }
        }
        return false;
    }
    public static List<String> moderatorURLS = List.of(

    );
    public static List<String> adminURLS = List.of(
            "/api/v1/role/**"
    );
}
