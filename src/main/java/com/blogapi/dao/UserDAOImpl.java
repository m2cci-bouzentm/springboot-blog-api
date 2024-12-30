package com.blogapi.dao;



import com.blogapi.entity.Role;
import com.blogapi.entity.User;
import com.blogapi.response.UniqueConstraintViolationException;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    public UserDAOImpl() {

    }

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findUser(String id) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String jpql = "SELECT u FROM User u where u.username = :username";
        TypedQuery<User> q = entityManager.createQuery(jpql, User.class);
        q.setParameter("username", username);

        return Optional.of(q.getSingleResult());
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }

        try {
            entityManager.persist(user);
        } catch (PersistenceException e) {
            throw new UniqueConstraintViolationException("Duplicate email or username");
        }
    }

    @Override
    @Transactional
    public User updateUsernameAndEmail(String id, String username, String email) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        user.setUsername(username);
        user.setEmail(email);

        return entityManager.merge(user);
    }

    @Override
    @Transactional
    public User updateUserAvatarUrl(String id, String avatarUrl) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        user.setAvatarUrl(avatarUrl);

        return entityManager.merge(user);
    }

    @Override
    @Transactional
    public User updateUserPassword(String id, String password) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        user.setPassword(password);

        return entityManager.merge(user);
    }

    @Override
    @Transactional
    public User updateUserRole(String id, Role role) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        user.setRole(role);

        return entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        User user = entityManager.find(User.class, id);

        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }

        entityManager.remove(user);
    }
}
