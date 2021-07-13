package com.example.bluedit.repository;

import com.example.bluedit.model.Post;
import com.example.bluedit.model.Subreddit;
import com.example.bluedit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
