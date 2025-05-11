package com.vee.Blogapp.repositories;

import com.vee.Blogapp.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo  extends JpaRepository<User,Long>{

    User findByEmail(String email);
    User findByMobile(String mobile);

}
