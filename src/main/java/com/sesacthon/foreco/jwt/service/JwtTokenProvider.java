package com.sesacthon.foreco.jwt.service;

import com.sesacthon.foreco.jwt.dto.SessionUser;
import com.sesacthon.foreco.member.entity.Member;
import com.sesacthon.foreco.member.entity.OAuth2Provider;
import com.sesacthon.foreco.member.repository.MemberRepository;
import com.sesacthon.infra.redis.RefreshToken;
import com.sesacthon.infra.redis.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 토큰 발급해주는 서비스
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  private final MemberRepository memberRepository;
  private final RefreshTokenRepository refreshTokenRepository;

  //  jwt를 생성해주는 secretKey
  private static String secretKey = "MFswDQYJKoZIhvcNAQEBBQADSgAwRwJAaJd167MUifo3ZSytpDPd9z7oSS+1KXjf\n"
      + "HJ3oMvI61Id26fdQHgWE1QMLcrhOmRzTxkCU+gesx5ANkSSIrPHNswIDAQAB";

  //accessToken 만료시간
  public static final Long ACCESS_TOKEN_EXPIRE_LENGTH_MS = 1000L * 60 * 60 * 24 * 7 * 4; //4주

  //refreshToken 만료시간
  public static final Long REFRESH_TOKEN_EXPIRE_LENGTH_MS = 1000L * 60 * 60 * 24 * 14; //2주

  private Key key;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    key = Keys.hmacShaKeyFor(keyBytes);
  }

  //accessToken을 사용하여 Authentication 객체를 생성한다.
  public Authentication getAuthentication(String accessToken) {
    Claims claims = extractAllClaims(accessToken); //claims 정보를 추출할때 유효성 체크를 시작한다.
    String role = claims.get("role").toString();
    String uuid = claims.getSubject();
    return new UsernamePasswordAuthenticationToken(getSessionUser(uuid), "",
        getGrantedAuthorities(role));
  }

  private SessionUser getSessionUser(String uuid) {
    UUID changeUuid = UUID.fromString(uuid);
    log.info("session user 를 만들기 위해서 uuid : {} 인 사람을 찾습니다.", uuid);
    Optional<Member> optionalMember = memberRepository.findById(changeUuid);

    if (optionalMember.isEmpty()) {
      throw new JwtException("유효하지 않은 토큰입니다.");
    }
    return new SessionUser(optionalMember.get());
  }

  private static List<GrantedAuthority> getGrantedAuthorities(String role) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(role));
    return grantedAuthorities;
  }

  /**
   * 게스트로 진입할때, 해당 유저를 DB에 저장하고 난 후의 UUID로 accessToken을 생성한다.
   * 유효시간은 1시간으로 설정해놓았다.
   */
  public String createAccessToken(UUID uuid, OAuth2Provider provider) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_LENGTH_MS);

    return Jwts.builder()
        .signWith(key, SignatureAlgorithm.HS256)
        .setSubject(uuid.toString())
        .claim("provider", provider)
        .setIssuer("foreco")
        .setIssuedAt(now)
        .setExpiration(validity)
        .compact();
  }

  /**
   * Refresh Token 을 생성한다.
   * 유효 시간은 14일로 설정한다.
   * @return 생성된 Refresh Token
   */
  public String createRefreshToken(UUID memberId) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_LENGTH_MS);

    String refreshToken = Jwts.builder()
        .signWith(key, SignatureAlgorithm.HS256)
        .setIssuer("foreco")
        .setIssuedAt(now)
        .setExpiration(validity)
        .compact();

    RefreshToken token = new RefreshToken(memberId, refreshToken);
    refreshTokenRepository.save(token);
    return refreshToken;
  }

  public Claims extractAllClaims(String token) throws ExpiredJwtException, MalformedJwtException, SignatureException {
    return Jwts.parserBuilder().setSigningKey(secretKey).build()
        .parseClaimsJws(token).getBody();
  }

  public void saveRefreshTokenInRedis(Member member, String refreshToken) {
    refreshTokenRepository.save(new RefreshToken(member.getId(), refreshToken));
  }

  // 만료된 시간이 언제인지 알려주는 메서드
  public Date getExpirationDate(String token) {
    return Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token)
        .getBody()
        .getExpiration();
  }

}
