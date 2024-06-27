package com.amazone_clone.backend.amazone_clone_backend.service.impl;

import com.amazone_clone.backend.amazone_clone_backend.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24))
                .signWith(getSigInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateRefreshToken(Map<String ,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSigInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUserName(String token){
        return extractClaims(token , Claims::getSubject);
    }


    private <T> T extractClaims(String token , Function<Claims,T> claimResolvers){
        final Claims claims = extractAllClaims(token);
        return claimResolvers.apply(claims);
    }

    private Key getSigInKey(){
        byte[] key = Decoders.BASE64.decode("yZsG8wIJ8iPdIX7R7oU1MwQFw4ZuA8f1aZG9kBtIkZs");
        return Keys.hmacShaKeyFor(key);

    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigInKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        final String userName = extractUserName(token);
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token , Claims::getExpiration).before(new Date());
    }

}
