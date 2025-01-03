package com.blogapi;

import com.blogapi.dao.CommentDAO;
import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class BlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner run(UserDAO userDao, PostDAO postDAO, CommentDAO commentDAO) {
//        return configurer -> {
//            System.out.println("APP is running !");
//        };
//    }
}
