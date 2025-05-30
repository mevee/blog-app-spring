package com.vee.Blogapp.services;

import com.vee.Blogapp.entites.User;
import com.vee.Blogapp.payloads.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto dto);
    UserDto findUserById(Long id);
    UserDto findUserByEmail(String email);
    UserDto findUserByMobile(String mobile);
    void deleteUser(Long id);
    void deleteBlockUser(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto dto);

}
