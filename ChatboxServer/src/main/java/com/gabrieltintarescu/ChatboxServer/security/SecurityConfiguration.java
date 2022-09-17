package com.gabrieltintarescu.ChatboxServer.security;

import com.gabrieltintarescu.ChatboxServer.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

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
        for (int i = 0; i < SecurityUtil.allowedURLS.size(); i++) {
            http.authorizeHttpRequests().antMatchers(
                    SecurityUtil.allowedURLS.get(i) + "/**"
            ).permitAll();
        }
        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }
}
