package com.codecool.codecoolquiz;

import com.codecool.codecoolquiz.config.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CodecoolquizApplication {

    @Autowired
    Initializer initializer;

    public static void main(String[] args) {
        SpringApplication.run(CodecoolquizApplication.class, args);
    }

    @PostConstruct
    public void afterInit() {
        initializer.loadInitData();
    }


}
