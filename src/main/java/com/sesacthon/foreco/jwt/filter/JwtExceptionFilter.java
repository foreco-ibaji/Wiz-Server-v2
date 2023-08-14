package com.sesacthon.foreco.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesacthon.foreco.jwt.exception.JwtException;
import com.sesacthon.global.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      filterChain.doFilter(request, response);
    } catch (JwtException e) {
      response.setStatus(e.getErrorCode().getStatus());
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      objectMapper.writeValue(response.getWriter(), ErrorResponse.of(e.getErrorCode()));
    }
  }
}
