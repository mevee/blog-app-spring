package com.vee.Blogapp.services.impl;

import com.vee.Blogapp.entites.User;
import com.vee.Blogapp.exceptions.ResourceNotFoundException;
import com.vee.Blogapp.payloads.UserDto;
import com.vee.Blogapp.repositories.UserRepo;
import com.vee.Blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto dto) {
        User user = userDtoToUser(dto);
        User result = userRepo.save(user);
        return this.userToDto(result);
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with Id: "+userId));
        return this.userToDto(user);
    }

    @Override
    public UserDto findUserByEmail(String email) {
         User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email : "+email));
        return this.userToDto(user);
    }

    @Override
    public UserDto findUserByMobile(String mobile) {
        User user = userRepo.findByMobile(mobile);
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with Id: "+id));
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
    public UserDto updateUser(UserDto dto) {
        User user = userRepo.findById(dto.getId()).orElseThrow(()->new ResourceNotFoundException("User with id "+dto.getId()+" not found"));
        user.setName(dto.getName());
        user.setMobile(dto.getMobile());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        userRepo.save(user);

        return userToDto(userRepo.save(user));
    }

    private UserDto userToDto(User user) {
        UserDto userDto =modelMapper.map(user,UserDto.class);
        userDto.setPassword(null);
        return userDto;
    }

    private User userDtoToUser(UserDto user) {
        return modelMapper.map(user,User.class);
    }
}
