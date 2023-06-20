package com.insearchofknowledge.bugTracker.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)  // for @Authorisation
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and().csrf()
                    .disable()
                .authorizeHttpRequests()                      // I want to authorize all requests
                    .requestMatchers("/api/auth/**") // that are enumerated here - WHITELIST
                    .permitAll()     // permit all for the above
                .anyRequest()        // anything else can only be accessed if user is
                    .authenticated() // AUTHENTICATED
                .and()               // we should not store the authentication / session state to ensure each request is authenticated
                .sessionManagement() // we will make stateless session so that each request SHOULD BE AUTHENTICATED
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // by creating a new session for each request
                .and()
                .authenticationProvider(authenticationProvider) // specify authentication provider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutUrl("/api/auth/logout")
                    .addLogoutHandler(logoutHandler)
                    .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
                .and()
                .headers()
                    .addHeaderWriter((request, response) ->{
                        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
                        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
                    });
        return httpSecurity.build();
    }
}
