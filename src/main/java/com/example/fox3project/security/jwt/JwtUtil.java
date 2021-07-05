package com.example.fox3project.security.jwt;

import com.example.fox3project.properties.JwtProperties;
import com.example.fox3project.security.CustomUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtUtil {

    private final String secret;

    public JwtUtil(JwtProperties jwtProperties) {
        secret = jwtProperties.getSecret();
    }

    public String generateToken(Authentication authentication){

        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            log.severe("invalid token");
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
