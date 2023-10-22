package com.sesacthon.foreco.jwt.controller;

import static com.sesacthon.foreco.member.service.MemberSignUpService.setCookieAndHeader;

import com.sesacthon.foreco.jwt.dto.ReIssueTokenDto;
import com.sesacthon.foreco.jwt.service.JwtTokenProvider;
import com.sesacthon.foreco.jwt.service.JwtTokenReissueService;
import com.sesacthon.global.response.MessageResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name="토큰 관련 컨트롤러", description = "토큰을 재발급할 수 있다.")
public class JwtController {
  private final JwtTokenReissueService jwtTokenReissueService;
  private final JwtTokenProvider jwtTokenProvider;

  @GetMapping("/api/v1/jwt/refresh")
  @Operation(summary = "Jwt 재발급", description = "Jwt를 재발급 할 수 있습니다.")
  public ResponseEntity<MessageResponse> reIssueToken(@CookieValue(name = "refreshToken") String refreshToken) {
    ReIssueTokenDto reIssueTokenDto = jwtTokenReissueService.reIssueToken(refreshToken);
    HttpHeaders headers = setCookieAndHeader(reIssueTokenDto);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "Token 재발급 성공"), headers, HttpStatus.CREATED);
  }

  @GetMapping("/api/v1/jwt/valid")
  @Operation(summary = "Jwt 유효성 검증", description = "Jwt의 유효성을 검증합니다.")
  public ResponseEntity<MessageResponse> isValidToken(@RequestParam("token") String token) {
    try {
      jwtTokenProvider.extractAllClaims(token);
    } catch (ExpiredJwtException e) {
      return new ResponseEntity<>(
          MessageResponse.of(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."), HttpStatus.UNAUTHORIZED);
    } catch (MalformedJwtException e) {
      return new ResponseEntity<>(
          MessageResponse.of(HttpStatus.BAD_REQUEST, "올바르지 않은 토큰 형식입니다."), HttpStatus.BAD_REQUEST);
    } catch (SignatureException e) {
      return new ResponseEntity<>(
          MessageResponse.of(HttpStatus.BAD_REQUEST, "jwt 시그니처가 올바르지 않습니다."), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.OK, "유효한 토큰입니다."), HttpStatus.OK);
  }

}
