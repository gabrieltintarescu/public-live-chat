package com.gabrieltintarescu.ChatboxServer.security;

import com.gabrieltintarescu.ChatboxServer.security.filter.CustomAuthenticationFilter;
import com.gabrieltintarescu.ChatboxServer.security.filter.CustomAuthorizationFilter;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/17/2022
 */
public class JwtHttpConfigurer extends AbstractHttpConfigurer<JwtHttpConfigurer, HttpSecurity> {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager);
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
