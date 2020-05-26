package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.Type;
import com.codecool.codecoolquiz.service.TypeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;

@RestController
public class TypeController {

    @Autowired
    TypeStorage typeStorage;

    @GetMapping("/types")
    public EnumSet<Type> getTypes() {
        return typeStorage.getAll();
    }
}
