package com.acledabankplc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    //not secure end point
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/authenticate/**",
            "/api/v1/cloudinary/upload/**",
            "/api/v1/products/create/**",
            "/api/v1/auth/**",
            "/api/v1/enrollment/**",
            "/api/v1/thirt-party/**",
            "/api/v1/course/all**",
            "/register/verifyEmail",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Disable CSRF for stateless APIs
                .cors(Customizer.withDefaults())         // Enable default CORS configuration
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(WHITE_LIST_URL).permitAll()  // Whitelist specific URLs
                        .anyRequest().authenticated()  // All other requests must be authenticated
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))  // Stateless session using JWT
                .authenticationProvider(authenticationProvider)  // Use the custom AuthenticationProvider
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before UsernamePasswordAuthenticationFilter

        return http.build();
    }
}