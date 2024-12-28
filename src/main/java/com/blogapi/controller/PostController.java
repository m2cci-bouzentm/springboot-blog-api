package com.blogapi.controller;


import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Post;
import com.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {


    private final PostService postService;

    @Autowired
    public PostController (PostService postService) {
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


}
