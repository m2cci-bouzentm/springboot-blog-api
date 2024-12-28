package com.blogapi.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role = Role.User;

    @Column(name = "avatarUrl")
    @Basic(optional = true)
    private String avatarUrl;


    //    relations
    @Column(name = "posts")
    @OneToMany(mappedBy = "author",
                cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts = null;

    @Column(name = "comments")
    @OneToMany(mappedBy = "author",
                cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = null;


    //    constructors
    public User() {
    }

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String password, Role role, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.avatarUrl = avatarUrl;
    }


    //    getters/setters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        if(comments == null) {
            comments = new ArrayList<>();
        }

        comments.add(comment);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        if(posts == null) {
            posts = new ArrayList<>();
        }

        posts.add(post);
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
