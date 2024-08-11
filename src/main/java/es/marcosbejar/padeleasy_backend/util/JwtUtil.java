package es.marcosbejar.padeleasy_backend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Log4j2
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.refreshsecret")
    private String refreshsecret;

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        }catch (Exception e) {
            return true;
        }
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_16);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getSigningRefKey() {
        byte[] keyBytes = this.refreshsecret.getBytes(StandardCharsets.UTF_16);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public boolean validateJwtToken(String authToken) throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (SignatureException e) {
            throw new SignatureException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token: " + e.getMessage(), e);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "JWT token is expired: " + e.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty: " + e.getMessage(), e);
        }
    }



    public boolean validateJwtRefreshToken(String authToken) throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException {
        try {
            Jwts.parser().verifyWith(getSigningRefKey()).build().parseSignedClaims(authToken);
            return true;
        } catch (SignatureException e) {
            throw new SignatureException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token: " + e.getMessage(), e);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "JWT token is expired: " + e.getMessage(), e);
        } catch (UnsupportedJwtException e) {
            throw new UnsupportedJwtException("JWT token is unsupported: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty: " + e.getMessage(), e);
        }
    }
}
