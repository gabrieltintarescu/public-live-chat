package com.gabrieltintarescu.ChatboxServer.security;

import com.gabrieltintarescu.ChatboxServer.security.util.SecurityUtil;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .apply(new JwtHttpConfigurer())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        for (String url : SecurityUtil.allowedURLS) {
            http.authorizeHttpRequests().antMatchers(
                    url + "/**"
            ).permitAll();
        }
        for (String url : SecurityUtil.moderatorURLS) {
            http.authorizeHttpRequests().antMatchers(HttpMethod.POST, url).hasAnyAuthority("ROLE_MODERATOR");
        }
        for (String url : SecurityUtil.adminURLS) {
            http.authorizeHttpRequests().antMatchers(HttpMethod.POST, url).hasAnyAuthority("ROLE_ADMIN");
        }

        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }
}
