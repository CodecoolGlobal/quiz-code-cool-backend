package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserStorage {

    @Autowired
    AppUserRepository appUserRepository;

    public void add(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public AppUser getByName(String name) {
        return appUserRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + name + " not found"));
    }
}
