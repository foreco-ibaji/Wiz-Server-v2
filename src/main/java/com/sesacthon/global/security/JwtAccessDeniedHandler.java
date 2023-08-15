package com.sesacthon.global.security;

import static com.sesacthon.global.exception.ErrorCode.RESOURCE_FORBIDDEN;

import com.sesacthon.global.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json;charset=UTF-8");
    objectMapper.writeValue(response.getWriter(), ErrorResponse.of(RESOURCE_FORBIDDEN));
  }
}