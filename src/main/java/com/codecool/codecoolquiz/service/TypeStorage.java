package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.Type;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class TypeStorage {

    public EnumSet<Type> getAll() {
        return EnumSet.allOf( Type.class);
    }
}
