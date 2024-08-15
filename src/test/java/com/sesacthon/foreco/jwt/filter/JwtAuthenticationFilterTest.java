package com.sesacthon.foreco.jwt.filter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sesacthon.foreco.jwt.service.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void testDoFilterInternal_토큰_형식이_잘못된_경우() throws Exception {
        // given
        request.setMethod("GET");
        request.setRequestURI("/api/v1/member");
        String nullToken = "";
        request.addHeader("Authorization", nullToken);

        // when, then
        assertThrows(IOException.class, () -> {
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        });
    }

    @Test
    void testDoFilterInternal_만료된_토큰인_경우() throws Exception {
        // given
        request.setMethod("GET");
        request.setRequestURI("/api/v1/member");

        String expiredToken = "Bearer " + "expired-token"; // 임의의 테스트 토큰
        request.addHeader("Authorization", expiredToken);

        Authentication mockAuthentication = mock(Authentication.class);
        when(jwtTokenProvider.getAuthentication("expired-token"))
            .thenThrow(new ExpiredJwtException(null, null, "만료된 토큰입니다."));

        // when, then
        assertThrows(SecurityException.class, () -> {
            jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        });
    }

    @Test
    void testDoFilterInternal_Authorization_정상적인_토큰인_경우() throws Exception {
        // given
        request.setMethod("GET");
        request.setRequestURI("/api/v1/member");

        String mockToken = "Bearer " + "some-test-token"; // 임의의 테스트 토큰
        request.addHeader("Authorization", mockToken);

        Authentication mockAuthentication = mock(Authentication.class);
        when(jwtTokenProvider.getAuthentication("some-test-token")).thenReturn(mockAuthentication);
        when(mockAuthentication.getName()).thenReturn("testUser");

        // when
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // then
        verify(filterChain, times(1)).doFilter(request, response);
        verify(jwtTokenProvider, times(1)).getAuthentication("some-test-token");
    }
}