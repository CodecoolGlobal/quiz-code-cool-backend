package com.codecool.codecoolquiz.model;

import org.springframework.stereotype.Component;

public class Category {
    private int id;
    private String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}