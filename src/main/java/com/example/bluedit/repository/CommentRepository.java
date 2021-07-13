package com.example.bluedit.repository;


import com.example.bluedit.model.Comment;
import com.example.bluedit.model.Post;
import com.example.bluedit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
