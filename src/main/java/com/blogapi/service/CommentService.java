package com.blogapi.service;


import com.blogapi.dao.CommentDAO;
import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;
import com.blogapi.dto.CommentDTO;
import com.blogapi.entity.Comment;
import com.blogapi.entity.CustomUserDetailsImpl;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentDAO commentDAO;
    private final PostDAO postDAO;
    private final UserDAO userDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO, PostDAO postDAO, UserDAO userDAO) {
        this.commentDAO = commentDAO;
        this.postDAO = postDAO;
        this.userDAO = userDAO;
    }

    public List<CommentDTO> getCommentsByPostId(String postId) {
        return commentDAO.findCommentsByPostId(postId);
    }


    public ResponseEntity<CommentDTO> createCommentOnPost(@PathVariable String postId, CommentDTO commentDTO) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            CustomUserDetailsImpl currentUser= (CustomUserDetailsImpl) authentication.getPrincipal();

            User author = userDAO.findUser(currentUser.getId());
            Post post = postDAO.findPostById(postId);
            Comment comment = new Comment(commentDTO.getContent(), new Date(System.currentTimeMillis()));

            post.addComment(comment);
            author.addComment(comment);

            comment.setAuthor(author);
            comment.setPost(post);


            userDAO.saveUser(author);
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    public ResponseEntity<CommentDTO> updateComment(String commentId, String postId,  CommentDTO commentDTO) {
            Comment comment = commentDAO.findById(commentId);
            User author = userDAO.findUser(comment.getAuthor().getId());
            Post post = postDAO.findPostById(postId);


            comment.setContent(commentDTO.getContent());
            comment.setAuthor(author);
            comment.setPost(post);

            commentDAO.saveComment(comment);

            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    public ResponseEntity<CommentDTO> deleteComment(String commentId) {
            CommentDTO commentDTO = new CommentDTO();
            commentDAO.deleteComment(commentId);
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
}
