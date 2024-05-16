package com.tech.ada.moviesbattle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import com.tech.ada.moviesbattle.repository.PlayerRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * class is responsible for configuring security measures within the application. It leverages Spring Security to
 * establish a security filter chain, ensuring that incoming HTTP requests are appropriately authenticated.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private PlayerRepository playerRepository;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                                authorizeRequests
                                        .anyRequest().authenticated()
                )
                .addFilterBefore(new XHeaderAuthenticationFilter(playerRepository), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
