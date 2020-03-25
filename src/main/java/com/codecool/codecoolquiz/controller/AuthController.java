package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.SignInResponseBody;
import com.codecool.codecoolquiz.model.SignUpResponseBody;
import com.codecool.codecoolquiz.model.UserCredentials;
import com.codecool.codecoolquiz.security.JwtTokenServices;
import com.codecool.codecoolquiz.service.AppUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

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
        SignUpResponseBody signUpResponseBody = appUserStorage.signUp(userCredentials);
        return ResponseEntity.ok().body(signUpResponseBody);
    }

    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody UserCredentials data) {
        try {
            String username = data.getUsername();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(username, roles);

            SignInResponseBody signInBody = new SignInResponseBody(username, token, roles);
            return ResponseEntity.ok().body(signInBody);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(403).build();
        }
    }
}
