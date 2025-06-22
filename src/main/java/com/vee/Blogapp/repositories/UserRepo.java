package com.vee.Blogapp.repositories;

import com.vee.Blogapp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);
    User findByMobile(String mobile);

}
