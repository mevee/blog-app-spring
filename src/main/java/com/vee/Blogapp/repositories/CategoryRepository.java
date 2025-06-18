package com.vee.Blogapp.repositories;

import com.vee.Blogapp.entites.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<PostCategory, Integer> {

}
