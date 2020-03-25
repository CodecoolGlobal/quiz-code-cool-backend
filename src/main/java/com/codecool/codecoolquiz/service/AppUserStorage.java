package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.model.SignUpResponse;
import com.codecool.codecoolquiz.model.UserCredentials;
import com.codecool.codecoolquiz.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    String unsuccessfulMessage = "Registration cannot be completed.\n%s is already taken.";
    public AppUser getByName(String name) {
        return appUserRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(unsuccessfulMessage, "Username")));
    }

    public SignUpResponse signUp(UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String email = userCredentials.getEmail();
        SignUpResponse signUpResponse = new SignUpResponse();
        if (appUserRepository.findByUsername(username).isPresent()) {
            signUpResponse.setSuccessful(false);
            signUpResponse.setResponseMessage(String.format(unsuccessfulMessage, "Email"));
            return signUpResponse;
        }
        if (appUserRepository.findByEmail(email).isPresent()) {
            signUpResponse.setSuccessful(false);
            signUpResponse.setResponseMessage("Email is already taken");
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

