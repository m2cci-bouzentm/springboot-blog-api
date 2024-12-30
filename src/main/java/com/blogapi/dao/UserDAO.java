package com.blogapi.dao;


import com.blogapi.entity.Comment;
import com.blogapi.entity.Role;
import com.blogapi.entity.User;

import java.util.Optional;

public interface UserDAO {
    User findUser(String id);
    Optional<User> findByUsername(String username);

    void saveUser(User user);


    User updateUsernameAndEmail(String id, String username, String email);

    User updateUserAvatarUrl(String id, String avatarUrl);

    User updateUserPassword(String id, String Password);

    User updateUserRole(String id, Role role);

    void deleteUser(String id);
}
