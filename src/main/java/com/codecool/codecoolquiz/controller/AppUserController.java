package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.exception.UserNotFoundException;
import com.codecool.codecoolquiz.service.AppUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    AppUserStorage appUserStorage;

    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable int userId) {
        try {
            return ResponseEntity.ok().body(appUserStorage.getUserBodyById(userId));
        } catch (UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }

}
