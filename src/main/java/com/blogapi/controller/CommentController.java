package com.blogapi.controller;


import com.blogapi.dto.CommentDTO;
import com.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
@CrossOrigin
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
    public ResponseEntity<CommentDTO> createCommentOnPost(@PathVariable String postId, @RequestBody CommentDTO commentDTO) {
        return commentService.createCommentOnPost(postId, commentDTO);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable String commentId, @PathVariable String postId, @RequestBody CommentDTO commentDTO) {
        return commentService.updateComment(commentId, postId, commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable String commentId) {
        return commentService.deleteComment(commentId);
    }
}
