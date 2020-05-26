package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.RequestResponseBody.UserResponseBody;
import com.codecool.codecoolquiz.service.AppUserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    AppUserStorage appUserStorage;

    @GetMapping("/{userId}")
    public UserResponseBody getUser(@PathVariable int userId) {
        return appUserStorage.getUserResponseBodyById(userId);
    }

    @GetMapping("")
    public List<UserResponseBody> getUsers() {
        return appUserStorage.getUsers();
    }

}
