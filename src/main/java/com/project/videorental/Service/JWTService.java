package com.project.videorental.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService{

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<String, Object>(), userDetails);
    }

    public String generateToken(Map<String,Object> additionalClaims, UserDetails userDetails){
        return Jwts.builder()
                    .addClaims(additionalClaims)
                    .setSubject(userDetails.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                    .compact();
    }


    public boolean isTokenValid(String token, UserDetails userDetails){
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }
    public Date extractExpirationDate(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims allClaims = extractAllClaims(token);
        return resolver.apply(allClaims);
    }

    public Claims extractAllClaims(String token){
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(getSignInKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return claims;
    }

    public Key getSignInKey(){
        byte[] secretInBinary = Decoders.BASE64.decode(jwtSecret);
        // byte[] secretInBinary = Base64.getDecoder().decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretInBinary);
    }

}