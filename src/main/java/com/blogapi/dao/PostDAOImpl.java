package com.blogapi.dao;

import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PostDAOImpl implements PostDAO {

    private EntityManager entityManager;
    private UserDAO userDAO;
    private CommentDAO commentDAO;

    public PostDAOImpl() {
    }

    @Autowired
    public PostDAOImpl(EntityManager entityManager, UserDAO userDAO, CommentDAO commentDAO) {
        this.entityManager = entityManager;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
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
    public List<PostDTO> findPostsByUserId(String userId) {
        String jpql1 = "SELECT " +
                "new com.blogapi.dto.PostDTO(p.id, p.title, p.content, p.isPublished, p.publishedAt, p.thumbnailUrl, p.author.id) " +
                "FROM Post p WHERE p.author.id = :id";

        TypedQuery<PostDTO> query = entityManager.createQuery(jpql1, PostDTO.class);

        query.setParameter("id", userId);

        return query.getResultList();
    }

    @Override
    public PostDTO findPostWithComments(String id) {
        Post post = entityManager.find(Post.class, id);
        if (post == null) {
            throw new EntityNotFoundException("Post not found with id " + id);
        }

        List<String> commentsId = new ArrayList<>();

        for (Comment comment : post.getComments()) {
            commentsId.add(comment.getId());
        }

        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.isPublished(),
                post.getPublishedAt(),
                post.getThumbnailUrl(),
                post.getAuthor().getId(),
                commentsId
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

        /*
    set to null the parent entities, do a merge, then make a flush and clear and finally,
    get the entity again using find and remove.
          */

        Post post = entityManager.find(Post.class, id);
        if (post == null) {
            throw new EntityNotFoundException("post not found with id " + id);
        }


        post.setAuthor(null);
        List<Comment> comments = post.getComments();
        for (Comment c : comments) {
            commentDAO.deleteComment(c.getId());
        }
        post.setComments(null);

        entityManager.merge(post);
        entityManager.flush();
        entityManager.clear();

        post = entityManager.find(Post.class, id);
        entityManager.remove(post);
    }
}
