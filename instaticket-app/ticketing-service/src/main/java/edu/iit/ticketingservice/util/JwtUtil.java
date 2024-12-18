package edu.iit.ticketingservice.util;

import edu.iit.ticketingservice.exception.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String AUTH_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String BASE64_SECRET_KEY = "khM6aydGJA7GDTm/tj8mZIgrOYnwnHvzI3RQQlSrJkxHana3zTIIu2CyC1QXH3H2stilCl7JdMyk5FRrMXNf5Q==";
    private final SecretKey SECRET_KEY;

    public JwtUtil() {
        byte[] keyBytes = Base64.getDecoder().decode(BASE64_SECRET_KEY);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String username,String userId) {


        return Jwts.builder()
                .issuedAt(new Date())
                .claim("userId", userId)  // Ensure this line is present
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
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

    // Method to extract JWT token from HttpServletRequest
    public String getTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(TOKEN_PREFIX.length());
        }
        return null; // Or handle this case as needed
    }

    public String extractUserId(HttpServletRequest httpServletRequest) {
         String token = getTokenFromRequest(httpServletRequest);
         return extractClaim(token, claims -> claims.get("userId", String.class));
    }

}
