package org.example.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    // jwt 생성 메서드
    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .issuer(jwtProperties.getIssuer())
                .issuedAt(now) // 내용 iat: 현재 시간
                .expiration(expiry) // 내용 exp: expiry 멤버 변수값
                .subject(user.getEmail()) // 내용 sub: 유저의 이메일
                .claim("id", user.getId()) // 클레임 id: 유저 ID
                .signWith(SignatureAlgorithm.forName("HS256"), jwtProperties.getSecretKey()) // 서명: 비밀값과 함께 해시값을 HS256 방식으로 암호화
                .compact();
    }

    // JWT 토큰 유효성 검증 메서드
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 비밀값으로 복호화
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (Exception e) { // 복호화 과정에서 에러가 나면 유효하지 않은 토큰
            log.info("Exception: " + e);
            return false;
        }
    }

    // 토큰 기반으로 인증 정보를 가져오는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities), token, authorities);
    }

    // 토큰 기반으로 유저 ID를 가져오는 메서드
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser() // 클레임 조회
                .setSigningKey(jwtProperties.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }
}
