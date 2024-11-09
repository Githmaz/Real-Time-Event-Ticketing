package edu.iit.ticketingservice.util;

import edu.iit.ticketingservice.exception.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Automatically generates a secure 512-bit key

    public String generateToken(String username,String userId) {
            return Jwts.builder()
                    .issuedAt(new Date())
                    .subject(username)
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS512) // Use HS512 and SECRET_KEY
                    .compact();

    }

    private SecretKey getKey() {
        return SECRET_KEY;
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T>T extractClaim(String token, Function<Claims,T> claimsExtractor) {
        final Claims claims = extractAllClaim(token);
        return claimsExtractor.apply(claims);
    }

    private Claims extractAllClaim(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
