package com.blogapi.controller;


import com.blogapi.dto.PostDTO;
import com.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {


    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostDTO getPosts(@PathVariable String postId) {
        return postService.getPostById(postId);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }


    @PutMapping
    public ResponseEntity<String> updatePost(@RequestBody PostDTO postDTO) {
        return postService.updatePost(postDTO);
    }

    @PutMapping("/publish/{postId}")
    public ResponseEntity<String> updatePostPublishState(@PathVariable String postId, @RequestBody PostDTO postDTO) {
        return postService.updatePostPublishState(postId, postDTO.isPublished());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        return postService.deletePost(postId);
    }


}
