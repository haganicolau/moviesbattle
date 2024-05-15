package com.tech.ada.moviesbattle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import com.tech.ada.moviesbattle.repository.PlayerRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                                .requestMatchers("/battlemovies/quiz").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/battlemovies/quiz").hasRole("USER")

                                .requestMatchers("/battlemovies/quiz/start").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/battlemovies/quiz/start").hasRole("USER")

                                .requestMatchers("/battlemovies/{idQuiz}/question").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/battlemovies/{idQuiz}/question").hasRole("USER")

                                        .anyRequest().authenticated()
                )
                .addFilterBefore(new XHeaderAuthenticationFilter(playerRepository), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
