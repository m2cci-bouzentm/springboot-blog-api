package com.blogapi.service;


import com.blogapi.dao.CommentDAO;
import com.blogapi.dao.PostDAO;
import com.blogapi.dao.UserDAO;
import com.blogapi.dto.CommentDTO;
import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public ResponseEntity<String> createCommentOnPost(CommentDTO commentDTO) {
        try {
            Comment comment = new Comment(commentDTO.getContent(), commentDTO.getPublishedAt());
            User author = userDAO.findUser(commentDTO.getAuthorId());
            Post post = postDAO.findPostById(commentDTO.getPostId());

            post.addComment(comment);
            author.addComment(comment);

            comment.setAuthor(author);
            comment.setPost(post);

            userDAO.saveUser(author);
            return new ResponseEntity<>("Created a comment successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> updateComment(String commentId, CommentDTO commentDTO) {

        try {
            Comment comment = commentDAO.findById(commentId);
            User author = userDAO.findUser(commentDTO.getAuthorId());
            Post post = postDAO.findPostById(commentDTO.getPostId());


            comment.setContent(commentDTO.getContent());
            comment.setAuthor(author);
            comment.setPost(post);

            commentDAO.saveComment(comment);

            return new ResponseEntity<>("Updated a comment successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> deleteComment(String commentId) {
        try {
            commentDAO.deleteComment(commentId);
            return new ResponseEntity<>("Deleted a comment successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
