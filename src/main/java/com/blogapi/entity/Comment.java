package com.blogapi.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "content", unique = true)
    private String content;


    @Column(name = "publishedAt")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date publishedAt = new Date(System.currentTimeMillis());


    //    relations
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;


    //  constructors
    public Comment() {
    }

    public Comment(String content, Date publishedAt) {
        this.content = content;
        this.publishedAt = publishedAt;
    }


    //    getters/setters
    public String getId() {
        return id;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", publishedAt=" + publishedAt +
                ", author=" + author.getUsername() +
                '}';
    }
}
