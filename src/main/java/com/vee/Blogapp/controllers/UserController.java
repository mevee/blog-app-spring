package com.vee.Blogapp.controllers;

import com.vee.Blogapp.payloads.ResponsePayload;
import com.vee.Blogapp.payloads.UserDto;
import com.vee.Blogapp.payloads.request.CreateUserRequest;
import com.vee.Blogapp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(name = "/")
    public ResponseEntity<ResponsePayload> createUser(@Valid @RequestBody UserDto request) {
        System.out.println("---------Request" + request + "--------");
//        if (request.getName().isEmpty()) return "Name is required";
//        if (request.getEmail().isEmpty()) return "Email is required";
//        if (request.getPassword().isEmpty()) return "Password is required";
//        if (request.getPassword().length()<6) return "Password must be minimum of 6 required";
        UserDto userDto = userService.createUser(request);
        var responsePayload = ResponsePayload.success();
         responsePayload.setData(userDto);
        return new ResponseEntity<>(responsePayload,HttpStatus.CREATED);
    }



    @GetMapping(name = "/{userId}")
    public ResponseEntity<ResponsePayload> createUser(@PathVariable Long userId) {
        System.out.println("---------Request" + userId + "--------");
//        if (request.getName().isEmpty()) return "Name is required";
//        if (request.getEmail().isEmpty()) return "Email is required";
//        if (request.getPassword().isEmpty()) return "Password is required";
//        if (request.getPassword().length()<6) return "Password must be minimum of 6 required";
        List<UserDto> userDto = userService.getAllUsers();
        var responsePayload = ResponsePayload.success();
        responsePayload.setData(userDto);
        return new ResponseEntity<>(responsePayload,HttpStatus.OK);
    }
    @GetMapping(name = "/{userId}")
    public ResponseEntity<ResponsePayload> deleteUser(@PathVariable Long userId) {
        System.out.println("---------Request" + userId + "--------");
         userService.deleteUser(userId);
        var responsePayload = ResponsePayload.success();
        return new ResponseEntity<>(responsePayload,HttpStatus.OK);
    }
}
