package com.blogapi.dao;

import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Post;

import java.util.List;

public interface PostDAO {

    List<PostDTO> findAllPosts();

    Post findPostById(String id);

    List<PostDTO> findPostsByUserId(String userId);

    PostDTO findPostWithComments(String id);

    void savePost(Post post);

    Post updatePostTitle(String id, String title);

    Post updatePostContent(String id, String content);

    Post updatePostPublishStatus(String id, boolean publishStatus);

    Post updatePostThumbnailUrl(String id, String thumbnailUrl);

    void deletePost(String id);
}
