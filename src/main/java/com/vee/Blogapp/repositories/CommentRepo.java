package com.vee.Blogapp.repositories;

import com.vee.Blogapp.entites.Comment;
import com.vee.Blogapp.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
 List<Comment> findByPostId(Integer postId);
// Page<Comment> findByUser(User user, Pageable pageable);
}

