package com.northstar.crm.config;

import com.northstar.crm.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(
          HttpSecurity http,
          JwtAuthenticationFilter jwtFilter) throws Exception {

    http
            .csrf(csrf -> csrf.disable())

            // No server-side login sessions
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                    // Public endpoints
                    .requestMatchers(
                            "/api/auth/login",
                            "/actuator/health",
                            "/error"
                    ).permitAll()

                    // Allow browser preflight requests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // Admin-only endpoints
                    .requestMatchers("/api/admin/**")
                    .hasRole("ADMIN")

                    // Customer endpoints
                    .requestMatchers("/api/customers/**")
                    .hasAnyRole("AGENT", "ADMIN")

                    // Everything else requires authentication
                    .anyRequest().authenticated()
            )

            // JWT instead of HTTP Basic
            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class)

            // Disable browser login/basic-auth behavior
            .formLogin(form -> form.disable())
            .httpBasic(httpBasic -> httpBasic.disable());

    return http.build();
  }
}