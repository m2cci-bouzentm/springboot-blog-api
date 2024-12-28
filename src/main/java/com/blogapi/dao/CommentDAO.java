package com.blogapi.dao;

import com.blogapi.dto.CommentDTO;
import com.blogapi.entity.Comment;

import java.util.List;

public interface CommentDAO {
    Comment findById(String id);
    List<CommentDTO> findCommentsByPostId(String postId);
    void saveComment(Comment comment );
    Comment updateComment(String id, String content);
    void deleteComment(String id);
}
