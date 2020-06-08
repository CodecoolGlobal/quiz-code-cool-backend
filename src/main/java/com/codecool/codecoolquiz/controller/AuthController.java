package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.model.RequestResponseBody.SignInResponseBody;
import com.codecool.codecoolquiz.model.UserCredentials;
import com.codecool.codecoolquiz.security.JwtTokenServices;
import com.codecool.codecoolquiz.service.AppUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${cookie.domain}")
    private String cookiedomain;

    @Value("${jwt.expiration.minutes:60}")
    private long cookieMaxAgeMinutes;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenServices jwtTokenServices;

    @Autowired
    AppUserStorage appUserStorage;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserCredentials userCredentials) {
        appUserStorage.signUp(userCredentials);
        return userCredentials.getUsername();
    }

    @PostMapping("/sign-in")
    public SignInResponseBody signIn(@RequestBody UserCredentials data, HttpServletResponse response) {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String token = jwtTokenServices.generateToken(authentication);
            addTokenToCookie(response, token);
            AppUser user = appUserStorage.getByName(username);
            return new SignInResponseBody(username, roles, user.getId(), cookieMaxAgeMinutes);
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .domain(cookiedomain) // should be parameterized
//                .sameSite("Strict")  // CSRF
//                .secure(true)
                .maxAge(Duration.ofMinutes(cookieMaxAgeMinutes))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    @PostMapping("/sign-out")
    public void signOut(HttpServletResponse response, HttpServletRequest request) {
        jwtTokenServices.eraseCookie(response, request);
    }

}
