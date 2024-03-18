package com.tunebaker.farm.util;

import com.tunebaker.farm.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    @Value("${jwt.lifetime}")
    private Duration lifeTime;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        String role = user.getRole().getName();
        claims.put("role", role);

        Date issued = new Date();
        Date expired = new Date(issued.getTime() + lifeTime.toMillis());

        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(user.getEmail())
                   .setIssuedAt(issued)
                   .setExpiration(expired)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    public String getUserEmail(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public String getRole(String token) {
        return getAllClaimsFromToken(token).get("role", String.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
