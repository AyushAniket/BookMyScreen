package com.microservices.user_service.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.lang.Strings;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenVerifierFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if(Strings.hasText(token) && token.startsWith("Bearer ")){
            token = token.replace("Bearer ", "");
            String key = "secret_secret_secret_secret_secret_secret_secret_secret_secret_secret";
            try {

                Key secretKey = Keys.hmacShaKeyFor(key.getBytes());

                JwtParser parser = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build();  // build the parser with the signing key

                Claims body = parser.parseClaimsJws(token).getBody();
                String email = body.getSubject();

                List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

                Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                        .map(a ->
                                new SimpleGrantedAuthority("ROLE_" + a.get("authority"))
                        ).collect(Collectors.toSet());

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);

            } catch (JwtException e) {
                throw new RuntimeException("Token is invalid!");
            }

        }else {
            filterChain.doFilter(request, response);
        }
    }
}
