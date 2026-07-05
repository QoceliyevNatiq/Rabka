package com.Rabka.rabka.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private  String jwtSecret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(token.getBytes())
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .setSubject(userDetails.getUsername())
                .compact();
    }
    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

   public boolean validateToken(String token, UserDetails userDetails){
        String username = extractUsername(token);
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(token.getBytes())
                .build()
                .parseClaimsJws(token).getBody()
                .getExpiration();
        return username.equals(userDetails.getUsername()) && expirationDate.after(new Date());
   }

}
