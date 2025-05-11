package com.vee.Blogapp.services.impl;

import com.vee.Blogapp.entites.User;
import com.vee.Blogapp.exceptions.ResourceNotFoundException;
import com.vee.Blogapp.payloads.UserDto;
import com.vee.Blogapp.repositories.UserRepo;
import com.vee.Blogapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto dto) {
        User user =userDtoToUser(dto);
        User result = userRepo.save(user);
        return this.userToDto(result);
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user =userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user =userRepo.findByEmail(email);
        return this.userToDto(user);
    }

    @Override
    public UserDto findUserByMobile(String mobile) {
        User user =userRepo.findByMobile(mobile);
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User","ID",id));
        userRepo.delete(user);
    }

    @Override
    public void deleteBlockUser(Long id) {
//        userRepo.block(id);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDto = users.stream().map(this::userToDto).toList();
        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto dto, Long id) {
        return null;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setMobile(user.getMobile());
//        userDto.setPassword(user.getPassword());
//        userDto.setCreatedAt(user.getCreatedAt());
//        userDto.setUpdatedAt(user.getUpdatedAt());
//        userDto.setImage(user.getImage());
//        userDto.setIsDeleted(user.getIsDeleted());
//        userDto.setBlockedBy(user.getBlockedBy());
//        userDto.setIsBlocked(user.getIsBlocked());
//        userDto.setIsEmailVerified(user.getIsEmailVerified());
//        userDto.setIsMobileVerified(user.getIsMobileVerified());
//        userDto.setAuthToken(user.getAuthToken());
        return userDto;
    }

    private User  userDtoToUser(UserDto user) {
        User userDto = new User();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setMobile(user.getMobile());
//        userDto.setPassword(user.getPassword());
//        userDto.setCreatedAt(user.getCreatedAt());
//        userDto.setUpdatedAt(user.getUpdatedAt());
//        userDto.setImage(user.getImage());
//        userDto.setIsDeleted(user.getIsDeleted());
//        userDto.setBlockedBy(user.getBlockedBy());
//        userDto.setIsBlocked(user.getIsBlocked());
//        userDto.setIsEmailVerified(user.getIsEmailVerified());
//        userDto.setIsMobileVerified(user.getIsMobileVerified());
//        userDto.setAuthToken(user.getAuthToken());
        return userDto;
    }
}
