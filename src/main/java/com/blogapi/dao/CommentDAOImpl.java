package com.blogapi.dao;

import com.blogapi.dto.CommentDTO;
import com.blogapi.dto.PostDTO;
import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {


    private EntityManager entityManager;

    public CommentDAOImpl() {
    }


    @Autowired
    public CommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment findById(String id) {
        Comment comment = this.entityManager.find(Comment.class, id);

        if (comment == null) {
            throw new EntityNotFoundException("Comment not found with id " + id);
        }

        return comment;
    }

    @Override
    public List<CommentDTO> findCommentsByPostId(String postId) {

        TypedQuery<Comment> q = entityManager.createQuery("SELECT c from Comment c WHERE c.post.id = :postId", Comment.class);
        q.setParameter("postId", postId);
        List<Comment> comments = q.getResultList();

        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment c : comments) {
            CommentDTO commentDTO = new CommentDTO(
                    c.getId(),
                    c.getContent(), c.getPublishedAt(),
                    c.getAuthor().getId(), c.getPost().getId()
            );

            commentsDTO.add(commentDTO);
        }

        return commentsDTO;
    }

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        if (!entityManager.contains(comment)) {
            comment = entityManager.merge(comment);
        }

        entityManager.persist(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(String id, String content) {

        Comment comment = entityManager.find(Comment.class, id);

        if (comment == null) {
            throw new EntityNotFoundException("Comment not found with id " + id);
        }

        comment.setContent(content);

        return entityManager.merge(comment);
    }


    @Override
    @Transactional
    public void deleteComment(String id) {
                /*
            set to null the parent entities, do a merge, then make a flush and clear and finally,
            get the entity again using find and remove.
                  */
        Comment comment = entityManager.find(Comment.class, id);
        if (comment == null) {
            throw new EntityNotFoundException("Comment not found with id " + id);
        }

        comment.setPost(null);
        comment.setAuthor(null);
        entityManager.merge(comment);
        entityManager.flush();
        entityManager.clear();


        comment = entityManager.find(Comment.class, id);
        entityManager.remove(comment);
    }
}
