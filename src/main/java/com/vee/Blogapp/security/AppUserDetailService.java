package com.vee.Blogapp.security;

import com.vee.Blogapp.entites.User;
import com.vee.Blogapp.exceptions.ResourceNotFoundException;
import com.vee.Blogapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with id:" + username + " not found"));
        return user;
    }
}
