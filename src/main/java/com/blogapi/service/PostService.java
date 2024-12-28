package com.blogapi.service;


import com.blogapi.dao.PostDAO;
import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostDAO postDAO;

    @Autowired
    public PostService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<PostDTO> getPosts() {
        return postDAO.findAllPosts();
    }

    public PostDTO getPostById(String postId) {
        return postDAO.findPostWithComments(postId);
    }
}
