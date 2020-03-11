package com.codecool.codecoolquiz;

import com.codecool.codecoolquiz.service.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CodecoolquizApplication {


    public static void main(String[] args) {
        SpringApplication.run(CodecoolquizApplication.class, args);
    }


}
