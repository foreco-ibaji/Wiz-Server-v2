package com.sesacthon.foreco.jwt.controller;

import static com.sesacthon.foreco.member.service.MemberSignUpService.setCookieAndHeader;

import com.sesacthon.foreco.jwt.dto.ReIssueTokenDto;
import com.sesacthon.foreco.jwt.service.JwtTokenReissueService;
import com.sesacthon.global.response.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="토큰 관련 컨트롤러", description = "토큰을 재발급할 수 있다.")
public class JwtController {
  private final JwtTokenReissueService jwtTokenReissueService;

  @GetMapping("/api/v1/jwt/refresh")
  @Operation(summary = "Jwt 재발급", description = "Jwt를 재발급 할 수 있습니다.")
  public ResponseEntity<MessageResponse> reIssueToken(@CookieValue(name = "refreshToken") String refreshToken) {
    ReIssueTokenDto reIssueTokenDto = jwtTokenReissueService.reIssueToken(refreshToken);
    HttpHeaders headers = setCookieAndHeader(reIssueTokenDto);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "Token 재발급 성공"), headers, HttpStatus.CREATED);
  }

}
