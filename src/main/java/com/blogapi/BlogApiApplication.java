package com.blogapi;

import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;
import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(UserDAO userDao, PostDAO postDAO) {
        return configurer -> {
//            var user = new User("mohamed12", "meail@gmail.com12", "123456", Role.Author);
//
//            var post2 = new Post("post2", "contentcontentcontentcontentcontent", new Date(System.currentTimeMillis()), true);
////
//            var userId = "b1c71ece-8957-4213-85c2-e2f6df4b2216";
//            var postId = "9553b847-3a6e-4b36-a2ea-780e47690099";
//
//            User user = userDao.findUser(userId);
//            PostDTO p = postDAO.findPostWithComments(postId);
//            Post post = postDAO.findPostById(p.getPostId());
//            Comment comment = new Comment("aaaaaaaaaaaaaaaaaaaaaaaa", new Date(System.currentTimeMillis()));
//
//            if (user == null) {
//                throw new EntityNotFoundException("User not found with id " + userId);
//            }
//            if (post == null) {
//                throw new EntityNotFoundException("Post not found with id " + postId);
//            }
//
//            comment.setAuthor(user);
//            comment.setPost(post);
//
//            user.addComment(comment);
//            post.addComment(comment);
//
//
//
//            userDao.saveUser(user);


            System.out.println(postDAO.findAllPosts());

        };
    }
}
