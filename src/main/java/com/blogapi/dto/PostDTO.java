package com.blogapi.dto;

import com.blogapi.entity.Comment;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@EntityScan
public class PostDTO {
    private String postId;
    private String title;
    private String content;
    private boolean isPublished;
    private Date publishedAt;
    private String thumbnailUrl;
    private String authorId;
    private List<Comment> comments = null;

    public PostDTO(String postId, String title, String content, boolean isPublished, Date publishedAt, String thumbnailUrl, String authorId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.publishedAt = publishedAt;
        this.thumbnailUrl = thumbnailUrl;
        this.authorId = authorId;
    }

    public PostDTO(String postId, String title, String content, boolean isPublished, Date publishedAt, String thumbnailUrl, String authorId, List<Comment> comments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.publishedAt = publishedAt;
        this.thumbnailUrl = thumbnailUrl;
        this.authorId = authorId;
        this.comments = comments;
    }


    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
