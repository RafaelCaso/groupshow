package com.groupshow.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    Dotenv dotenv = Dotenv.load();

    private final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");
    private final Long JWT_ACCESS_EXP_TIME_MS = 1000 * 60 * 60L;
    private final Long JWT_REFRESH_EXP_TIME_MS = 1000 * 60 * 60 * 24L;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails, JwtTokenType jwtTokenType) {
        return generateToken(new HashMap<>(), userDetails, jwtTokenType);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            JwtTokenType jwtTokenType
    ) {
        if (jwtTokenType.equals(JwtTokenType.ACCESS)) {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_EXP_TIME_MS))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

        } else if (jwtTokenType.equals(JwtTokenType.REFRESH)) {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_EXP_TIME_MS))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
        }
        return null;
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        // "username" is actually the user email
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
