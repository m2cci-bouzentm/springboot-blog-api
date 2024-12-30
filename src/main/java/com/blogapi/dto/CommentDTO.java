package com.blogapi.dto;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@EntityScan
public class CommentDTO {
    private String commentId;
    private String content;
    private Date publishedAt;
    private String authorId;
    private String postId;


    public CommentDTO() {
    }

    public CommentDTO(String commentId, String content, Date publishedAt, String authorId, String postId) {
        this.commentId= commentId;
        this.content = content;
        this.publishedAt = publishedAt;
        this.authorId = authorId;
        this.postId = postId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
