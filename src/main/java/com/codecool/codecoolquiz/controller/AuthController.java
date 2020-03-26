package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.SignInResponseBody;
import com.codecool.codecoolquiz.model.UserCredentials;
import com.codecool.codecoolquiz.model.exception.EmailAlreadyExistException;
import com.codecool.codecoolquiz.model.exception.SignUpException;
import com.codecool.codecoolquiz.model.exception.UsernameAlreadyExistException;
import com.codecool.codecoolquiz.security.JwtTokenServices;
import com.codecool.codecoolquiz.service.AppUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenServices jwtTokenServices;

    @Autowired
    AppUserStorage appUserStorage;

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody UserCredentials userCredentials) {
        try {
            appUserStorage.signUp(userCredentials);
            return ResponseEntity.ok().body(userCredentials.getUsername());
        } catch (EmailAlreadyExistException e) {
            return ResponseEntity.status(409).body(SignUpException.EMAIL);
        } catch (UsernameAlreadyExistException e) {
            return ResponseEntity.status(409).body(SignUpException.USERNAME);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody UserCredentials data, HttpServletResponse response) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            String token = jwtTokenServices.generateToken(authentication);
            addTokenToCookie(response, token);
            SignInResponseBody signInBody = new SignInResponseBody(username, roles);
            return ResponseEntity.ok().body(signInBody);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(403).build();
        }
    }

    private void addTokenToCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .domain("localhost") // should be parameterized
                .sameSite("Strict")  // CSRF
//                .secure(true)
                .maxAge(Duration.ofHours(10))
                .httpOnly(true)      // XSS
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
