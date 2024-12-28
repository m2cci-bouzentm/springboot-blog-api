package com.blogapi.dao;

import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PostDAOImpl implements PostDAO {

    private EntityManager entityManager;

    public PostDAOImpl() {
    }

    @Autowired
    public PostDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Post findPostById(String id) {
        Post post = this.entityManager.find(Post.class, id);

        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        return post;
    }

    @Override
    public List<PostDTO> findAllPosts() {

        String jpql1 = "SELECT " +
                "new com.blogapi.dto.PostDTO(p.id, p.title, p.content, p.isPublished, p.publishedAt, p.thumbnailUrl, p.author.id) " +
                "FROM Post p";

        TypedQuery<PostDTO> query = entityManager.createQuery(jpql1, PostDTO.class);

        return query.getResultList();
    }

    @Override
    public PostDTO findPostWithComments(String id) {
        Post post = entityManager.find(Post.class, id);
        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.isPublished(),
                post.getPublishedAt(),
                post.getThumbnailUrl(),
                post.getAuthor().getId(),
                post.getComments()
        );
    }


    @Override
    @Transactional
    public void savePost(Post post) {
        if (!entityManager.contains(post)) {
            post = entityManager.merge(post);
        }

        entityManager.persist(post);
    }

    @Override
    @Transactional
    public Post updatePostTitle(String id, String title) {

        Post post = entityManager.find(Post.class, id);

        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        post.setTitle(title);

        return entityManager.merge(post);
    }

    @Override
    @Transactional
    public Post updatePostContent(String id, String content) {

        Post post = entityManager.find(Post.class, id);

        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        post.setContent(content);

        return entityManager.merge(post);
    }

    @Override
    @Transactional
    public Post updatePostPublishStatus(String id, boolean publishStatus) {
        Post post = entityManager.find(Post.class, id);

        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        post.setIsPublished(publishStatus);

        return entityManager.merge(post);
    }

    @Override
    @Transactional
    public Post updatePostThumbnailUrl(String id, String thumbnailUrl) {
        Post post = entityManager.find(Post.class, id);

        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        post.setThumbnailUrl(thumbnailUrl);

        return entityManager.merge(post);
    }

    @Override
    @Transactional
    public void deletePost(String id) {
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
    }
}
