package com.blogapi;

import com.blogapi.dao.CommentDAO;
import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;

import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;



@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(UserDAO userDao, PostDAO postDAO, CommentDAO commentDAO) {
        return configurer -> {

//            var user = new User("username", "String@email.com", , Role role);

//            userDao.deleteUser("13c4f93a-4462-4cf9-a8cf-c67f13269aca");
//            System.out.println(userDao.findUser("6b364b06-4504-4a09-a899-00c3670ea2f6"));
        };
    }
}
