package com.blogapi.controller;


import com.blogapi.dto.CommentDTO;
import com.blogapi.entity.Comment;
import com.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping
    public List<CommentDTO> getCommentsByPostId(@PathVariable String postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public ResponseEntity<String> createCommentOnPost(@RequestBody CommentDTO commentDTO) {
        return commentService.createCommentOnPost(commentDTO);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable String commentId, @RequestBody CommentDTO commentDTO) {
        return commentService.updateComment(commentId, commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
        return commentService.deleteComment(commentId);
    }
}
