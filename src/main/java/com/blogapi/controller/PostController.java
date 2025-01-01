package com.blogapi.controller;


import com.blogapi.dto.PostDTO;
import com.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
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
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }


    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable String postId) {
        return postService.updatePost(postDTO, postId);
    }

    @PutMapping("/publish/{postId}")
    public ResponseEntity<String> updatePostPublishState(@PathVariable String postId, @RequestBody PostDTO postDTO) {
        return postService.updatePostPublishState(postId, postDTO.getIsPublished());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable String postId) {
        return postService.deletePost(postId);
    }


}
