package com.blogapi.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition="text")
    private String content;

    @Column(name = "isPublished", columnDefinition = "BOOLEAN")
    private boolean isPublished = true;

    @Column(name = "publishedAt")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date publishedAt = new Date(System.currentTimeMillis());


    @Column(name = "thumbnailUrl")
    @Basic(optional = true)
    private String thumbnailUrl = null;


    //    relations
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "comments")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = null;


    //    constructors
    public Post() {
    }

    public Post(String title, String content, Date publishedAt,
                boolean isPublished, String thumbnailUrl) {
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.isPublished = isPublished;
        this.thumbnailUrl = thumbnailUrl;
    }

    public Post(String title, String content,
                Date publishedAt, boolean isPublished) {
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.isPublished = isPublished;
    }


    //    getters / setters
    public String getId() {
        return id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if(this.comments == null){
            this.comments = new ArrayList<>();
        }

        this.comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isPublished=" + isPublished +
                ", publishedAt=" + publishedAt +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
