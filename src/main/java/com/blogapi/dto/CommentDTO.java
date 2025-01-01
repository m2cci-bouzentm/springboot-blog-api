package com.blogapi.dto;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@EntityScan
public class CommentDTO {
    private String id;
    private String content;
    private Date publishedAt;
    private UserDTO author;
    private String postId;


    public CommentDTO() {
    }

    public CommentDTO(String id, String content, Date publishedAt, UserDTO author, String postId) {
        this.id = id;
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
