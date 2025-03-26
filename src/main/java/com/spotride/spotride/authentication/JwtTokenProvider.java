package com.spotride.spotride.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * JWT token provider.
 */
@Slf4j
@Component
public class JwtTokenProvider {

    private static final String JWT_SECRET = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
    private static final int JWT_EXPIRATION_IN_MS = 3600000; // 1 hour

    /**
     * Generate token.
     *
     * @param userEmail User email
     * @return generated token
     */
    public String generateToken(String userEmail) {
        var now = new Date();
        var expiryDate = new Date(now.getTime() + JWT_EXPIRATION_IN_MS);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Get User email by token.
     *
     * @param token token
     * @return User email
     */
    public String getUserEmailFromToken(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Validate token.
     *
     * @param token token that must be validated
     * @return {@link ValidateTokenStatus#VALID} if token valid, otherwise {@link ValidateTokenStatus#INVALID}
     */
    public ValidateTokenStatus validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            log.info("Token is valid");
            return ValidateTokenStatus.VALID;
        } catch (Exception ex) {
            log.error("Invalid JWT Token: {}", ex.getMessage());
            return ValidateTokenStatus.INVALID;
        }
    }

    /**
     * Gets sign key.
     *
     * @return key
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Validate status of the token.
     */
    public enum ValidateTokenStatus {
        VALID(true),
        INVALID(false);

        public final boolean isValid;

        ValidateTokenStatus(boolean isValid) {
            this.isValid = isValid;
        }
    }
}
