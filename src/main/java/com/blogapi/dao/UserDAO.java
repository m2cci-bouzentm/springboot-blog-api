package com.blogapi.dao;


import com.blogapi.entity.Comment;
import com.blogapi.entity.Role;
import com.blogapi.entity.User;

public interface UserDAO {
    User findUser(String id);

    void saveUser(User user);


    User updateUsernameAndEmail(String id, String username, String email);

    User updateUserAvatarUrl(String id, String avatarUrl);

    User updateUserPassword(String id, String Password);

    User updateUserRole(String id, Role role);

    void deleteUser(String id);
}
