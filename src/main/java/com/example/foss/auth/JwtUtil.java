package com.example.foss.auth;

import com.example.foss.dto.MemberDto;
import com.example.foss.exception.CustomErrorCode;
import com.example.foss.exception.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final Key key;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.token.access-expiration-time}")
    private long accessExpirationTime;

    @Value("${spring.jwt.token.refresh-expiration-time}")
    private long refreshExpirationTime;

    @Value("${spring.jwt.token.issuer}")
    private String issuer;

    @Autowired
    public JwtUtil(@Value("${spring.jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // access token 생성
    public String createAccessToken(MemberDto.MemberInfoDto member) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(member.getMemberId()));
        claims.put("memberId", member.getMemberId());
        claims.put("email", member.getEmail());

        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256) // 토큰 서명
                .compact();
    }

    // refresh token 생성
    public String createRefreshToken(MemberDto.MemberInfoDto member) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(member.getMemberId())); // Include user ID claim
        claims.put("memberId", member.getMemberId());


        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256) // 토큰 서명
                .compact();
    }

    // 토큰에서 userId 추출
    public Long getUserId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            System.out.println(token);
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();
            // 필수 claims 검사
            return claims.getExpiration().after(new Date()) && // 만료되지 않음
                    claims.getIssuer().equals(issuer); // 시스템에서 발급
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token: {}", e.getMessage());
            throw new CustomException(CustomErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw new CustomException(CustomErrorCode.EXPIRED_TOKEN);
        }catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new CustomException(CustomErrorCode.UNSUPPORTED_TOKEN);
        }catch (IllegalStateException e) {
            log.info("JWT claims String is empty.", e);
            throw new CustomException(CustomErrorCode.ILLEGAL_ARGUMENT_TOKEN);
        }
    }

    // JWT Claims 추출
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
