package co.edu.uniquindio.unimarket.security.services;

import co.edu.uniquindio.unimarket.security.model.PersonDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refresh_expiration}")
    private long refreshExpiration;
    public String generateToken(UserDetails personDetails) {
        return buildToken(new HashMap<>(), personDetails, jwtExpiration);
    }
    public String generateRefreshToken(UserDetails personDetails) {
        return buildToken(new HashMap<>(), personDetails, refreshExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails personDetails, long
            expiration) {
        List<String> roles =
                personDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        extraClaims.put("id", ((PersonDetailsImpl) personDetails).getId() );
        extraClaims.put("name", ((PersonDetailsImpl) personDetails).getName() );
        extraClaims.put("roles", roles);
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(personDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public boolean isTokenValid(String token, UserDetails personDetails) {
        final String person = extractUsername(token);
        return (person.equals(personDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
