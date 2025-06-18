package com.vee.Blogapp.repositories;

import com.vee.Blogapp.entites.Post;
import com.vee.Blogapp.entites.PostCategory;
import com.vee.Blogapp.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(PostCategory user, Pageable pageable);

    Page<Post> findByTitleContaining(String query, Pageable pageable);

}
