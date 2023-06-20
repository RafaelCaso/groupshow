package com.groupshow.security;

import com.groupshow.token.TokenType;
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
    private final static Long JWT_ACCESS_EXP_TIME_MS = 1000 * 60 * 30L;
    private final static Long JWT_REFRESH_EXP_TIME_MS = 1000 * 60 * 60 * 6L;

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails, TokenType tokenType) {
        return generateToken(new HashMap<>(), userDetails, tokenType);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            TokenType tokenType
    ) {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + getExpirationTimeMs(tokenType)))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
    }

    private Long getExpirationTimeMs(TokenType tokenType) {
        if (tokenType.equals(TokenType.ACCESS)) {
            return JWT_ACCESS_EXP_TIME_MS;
        } else if (tokenType.equals(TokenType.REFRESH)) {
            return JWT_REFRESH_EXP_TIME_MS;
        } else {
            return 0L;
        }
    }

    public Boolean isTokenValid(String jwt, UserDetails userDetails) {
        // "username" is actually the user email
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);

    }

    private Boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }
}
