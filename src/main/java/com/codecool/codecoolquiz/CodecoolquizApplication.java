package com.codecool.codecoolquiz;

import com.codecool.codecoolquiz.service.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CodecoolquizApplication {

    @Autowired
    Initializer initializer;

    public static void main(String[] args) {
        SpringApplication.run(CodecoolquizApplication.class, args);
    }

    @PostConstruct
    @Profile("production")
    public void afterInit() throws Exception {
        initializer.loadInitData();
    }


}
