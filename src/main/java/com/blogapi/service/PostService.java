package com.blogapi.service;


import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;
import com.blogapi.dto.PostDTO;
import com.blogapi.entity.CustomUserDetailsImpl;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDAO postDAO;
    private final UserDAO userDAO;

    @Autowired
    public PostService(PostDAO postDAO, UserDAO userDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    public List<PostDTO> getPosts() {
        return postDAO.findAllPosts();
    }

    public PostDTO getPostById(String postId) {
        return postDAO.findPostWithComments(postId);
    }

    public ResponseEntity<String> createPost(PostDTO postDTO) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
            User author = userDAO.findUser(userDetails.getId());


            Post post = new Post(postDTO.getTitle(), postDTO.getContent(),
                                postDTO.getPublishedAt(), postDTO.isPublished(),
                                postDTO.getThumbnailUrl());

            post.setAuthor(author);
            author.addPost(post);

            userDAO.saveUser(author);

            return new ResponseEntity<>("Added a post successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> updatePost(PostDTO postDTO) {

        try {
            Post post = postDAO.findPostById(postDTO.getPostId());
            User author = userDAO.findUser(postDTO.getAuthorId());


            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setPublishedAt(postDTO.getPublishedAt());
            post.setIsPublished(postDTO.isPublished());
            post.setThumbnailUrl(postDTO.getThumbnailUrl());
            post.setAuthor(author);

            userDAO.saveUser(author);
            return new ResponseEntity<>("Updated a post successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> updatePostPublishState(String postId, boolean publishStatus) {
        try {
            postDAO.updatePostPublishStatus(postId, publishStatus);
            return new ResponseEntity<>("Updated post's published status successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<String> deletePost(String postId) {
        try {
            postDAO.deletePost(postId);
            return new ResponseEntity<>("Deleted a post successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
