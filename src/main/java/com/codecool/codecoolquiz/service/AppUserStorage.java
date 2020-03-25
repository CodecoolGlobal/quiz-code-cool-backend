package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.model.SignUpResponseBody;
import com.codecool.codecoolquiz.model.UserCredentials;
import com.codecool.codecoolquiz.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AppUserStorage {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    AppUserRepository appUserRepository;

    public void add(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public AppUser getByName(String name) {
        return appUserRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("Username is not found"));
    }

    public SignUpResponseBody signUp(UserCredentials userCredentials) throws BadCredentialsException {
        String unsuccessfulMessage = "Registration cannot be completed.\n%s is already taken.";

        String username = userCredentials.getUsername();
        String email = userCredentials.getEmail();
        SignUpResponseBody signUpResponse = new SignUpResponseBody();

        if (appUserRepository.findByUsername(username).isPresent()) {
            signUpResponse.setSuccessful(false);
            signUpResponse.setResponseMessage(String.format(unsuccessfulMessage, "Username"));
            return signUpResponse;
        }
        if (appUserRepository.findByEmail(email).isPresent()) {
            signUpResponse.setSuccessful(false);
            signUpResponse.setResponseMessage(String.format(unsuccessfulMessage, "Email"));
            return signUpResponse;
        }
        appUserRepository.save(AppUser
                .builder()
                .username(username)
                .password(encoder.encode(userCredentials.getPassword()))
                .role("USER")
                .email(email)
                .registrationDate(LocalDate.now())
                .build()
        );
        signUpResponse.setSuccessful(true);
        signUpResponse.setResponseMessage("Successful registration!");
        return signUpResponse;
    }

}

