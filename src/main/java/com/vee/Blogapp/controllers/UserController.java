package com.vee.Blogapp.controllers;

import com.vee.Blogapp.payloads.ResponsePayload;
import com.vee.Blogapp.payloads.ResponsePayloadData;
import com.vee.Blogapp.payloads.UserDto;
import com.vee.Blogapp.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        logger.info("Ping received");
        var payload = ResponsePayload.success();
        return ResponseEntity.ok(payload);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto request) {
        logger.info("Create user request: {}", request);
        UserDto userDto = userService.createUser(request);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success",201,userDto));
    }

    @PutMapping("/")
    public ResponseEntity<ResponsePayload> updateUser(@Valid @RequestBody UserDto request) {
        logger.info("Update user request: ID = {}, body = {}", request);
        UserDto userDto = userService.updateUser(request);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success",200,userDto));

    }

    @GetMapping("/all")
    public ResponseEntity<ResponsePayload> getAllUsers() {
        logger.info("Fetch all users");
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, users));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponsePayload> getUserById(@PathVariable Long userId) {
        logger.info("Fetch user by ID: {}", userId);
        var user = userService.findUserById(userId);
        return ResponseEntity.ok(new ResponsePayloadData<>("Success", 200, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponsePayload> deleteUser(@PathVariable Long userId) {
        logger.info("Delete user by ID: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.ok(ResponsePayload.success());
    }
}
