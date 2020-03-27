package com.codecool.codecoolquiz.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtTokenServices {

    private Key secretKey;

    @PostConstruct
    private void initKey() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 36000000; // 10h

    private final String rolesFieldName = "roles";

    // Creates a JWT token
    public String generateToken(Authentication authentication) {
        List<String> roles = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(secretKey)
                .compact();
    }

    // checks if the token is valid and not expired.
    boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.debug("JWT token invalid " + e);
        }
        return false;
    }
    /**
     * Parses the username and roles from the token. Since the token is signed we can be sure its valid information.
     * Note that it does not make a DB call to be super fast!
     * This could result in returning false data (e.g. the user was deleted, but their token has not expired yet)
     * To prevent errors because of this make sure to check the user in the database for more important calls!
     */
    Authentication parseUserFromTokenInfo(String token) throws UsernameNotFoundException {
        Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String username = body.getSubject();
        List<String> roles = (List<String>) body.get(rolesFieldName);
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }

    public UsernamePasswordAuthenticationToken validateTokenAndExtractUserSpringToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        ArrayList<String> rolesList = claims.get("roles", ArrayList.class);
        List<SimpleGrantedAuthority> roles =
                rolesList.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                roles);
    }
}
