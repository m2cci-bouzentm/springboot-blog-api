package com.blogapi.service;


import com.blogapi.dao.CommentDAO;
import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;
import com.blogapi.dto.CommentDTO;
import com.blogapi.dto.PostDTO;
import com.blogapi.dto.UserDTO;
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
    private final CommentDAO commentDAO;

    @Autowired
    public PostService(PostDAO postDAO, UserDAO userDAO, CommentDAO commentDAO) {
        this.postDAO = postDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
    }

    public List<PostDTO> getPosts() {
        return postDAO.findAllPosts();
    }

    public PostDTO getPostById(String postId) {
        PostDTO post = postDAO.findPostWithComments(postId);
        List<CommentDTO> comments = commentDAO.findCommentsByPostId(postId);
        User author = userDAO.findUser(post.getAuthorId());

        UserDTO authorDto= new UserDTO(author);

        post.setAuthor(authorDto);
        post.setComments(comments);
        return post;
    }

    public ResponseEntity<PostDTO> createPost(PostDTO postDTO) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
            User author = userDAO.findUser(userDetails.getId());


            Post post = new Post(postDTO.getTitle(), postDTO.getContent(),
                                postDTO.getPublishedAt(), postDTO.getIsPublished(),
                                postDTO.getThumbnailUrl());

            post.setAuthor(author);
            author.addPost(post);

            userDAO.saveUser(author);

            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<PostDTO> updatePost(PostDTO postDTO, String postId) {

        try {
            Post post = postDAO.findPostById(postId);


            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setThumbnailUrl(postDTO.getThumbnailUrl());


            postDAO.savePost(post);
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
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


    public ResponseEntity<PostDTO> deletePost(String postId) {
        try {
            PostDTO postDTO  = new PostDTO();
            postDAO.deletePost(postId);
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
