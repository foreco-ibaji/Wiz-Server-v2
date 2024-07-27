package com.sesacthon.global.security;

import static org.springframework.security.config.http.SessionCreationPolicy.*;

import com.sesacthon.foreco.jwt.filter.JwtAuthenticationEntryPointHandler;
import com.sesacthon.foreco.jwt.filter.JwtAuthenticationFilter;
import com.sesacthon.foreco.jwt.filter.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;
  private final JwtAuthenticationEntryPointHandler jwtAuthenticationEntryPointHandler;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .sessionManagement(session ->
            session.sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(authorize ->
            authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                     .requestMatchers(HttpMethod.POST, "/api/v1/search/ai").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/member").authenticated()
                     .requestMatchers("/**").permitAll())
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

    http.exceptionHandling(exception ->
        exception.authenticationEntryPoint(jwtAuthenticationEntryPointHandler)
            .accessDeniedHandler(jwtAccessDeniedHandler));

    return http.build();
  }
}
