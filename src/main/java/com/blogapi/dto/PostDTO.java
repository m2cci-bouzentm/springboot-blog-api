package com.blogapi.dto;

import com.blogapi.entity.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@EntityScan
public class PostDTO {
    private String id;
    private String title;
    private String content;
    private boolean isPublished;
    private Date publishedAt;
    private String thumbnailUrl;
    private String authorId;
    private UserDTO author;
    private List<String> commentsId;
    private List<CommentDTO> comments;


    //    empty constructor is a must  to be able to populate the body in the incoming requests
    public PostDTO() {
    }

    public PostDTO(String id, String title, String content, boolean isPublished, Date publishedAt, String thumbnailUrl, String authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.publishedAt = publishedAt;
        this.thumbnailUrl = thumbnailUrl;
        this.authorId = authorId;
    }

    public PostDTO(String id, String title, String content, boolean isPublished, Date publishedAt, String thumbnailUrl, String authorId, List<String> commentsId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.publishedAt = publishedAt;
        this.thumbnailUrl = thumbnailUrl;
        this.authorId = authorId;
        this.commentsId = commentsId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean published) {
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

    public List<String> getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(List<String> commentsId) {
        this.commentsId = commentsId;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }


    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "postId='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isPublished=" + isPublished +
                ", publishedAt=" + publishedAt +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", authorId='" + authorId + '\'' +
                ", comments=" + commentsId +
                '}';
    }
}
