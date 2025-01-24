package edu.iit.ticketingservice.config;

import edu.iit.ticketingservice.service.impl.UserDetailsServiceImpl;
import edu.iit.ticketingservice.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("JwtFilter started processing request");

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtUtil.extractUserName(token);
                logger.info("Extracted Username: {}", username);
            } catch (ExpiredJwtException e) {
                logger.error("JWT token has expired");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "JWT token has expired");
                return;
            } catch (io.jsonwebtoken.SignatureException e) {
                logger.error("Invalid JWT signature");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT signature");
                return;
            } catch (MalformedJwtException e) {
                logger.error("Malformed JWT token");
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Malformed JWT token");
                return;
            } catch (Exception e) {
                logger.error("Invalid JWT token format");
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid JWT token format");
                return;
            }
        }
        // Proceed with authentication if username is extracted successfully
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = applicationContext.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username);
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                logger.info("User authenticated and security context updated for user: {}", username);
            }
        }

        filterChain.doFilter(request, response);
        logger.info("JwtFilter completed processing request");
    }
}
