package com.softend.nikah.controller;


import com.softend.nikah.base.ResponseHandler;
import com.softend.nikah.entity.User;
import com.softend.nikah.service.UserService;
import com.softend.nikah.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;


    // build create User REST API
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            User result = userService.createUser(user);
            return ResponseHandler.generateResponse(Constants.CREATED_SUCCESSFULLY, result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(true,e.getMessage(), null, HttpStatus.MULTI_STATUS);
        }

    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long userId) {
        try {
            User result = userService.getUserById(userId);
            return ResponseHandler.generateResponse(Constants.FETCHED_SUCCESSFULLY, result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(true, e.getMessage(), null, HttpStatus.MULTI_STATUS);
        }
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<User> result = userService.getAllUsers();
            return ResponseHandler.generateResponse(Constants.FETCHED_SUCCESSFULLY, result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(true, e.getMessage(), null, HttpStatus.MULTI_STATUS);
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        user.setId(userId);
        try {
            User result = userService.updateUser(user);
            return ResponseHandler.generateResponse(Constants.MODIFIED_SUCCESSFULLY, result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(true, e.getMessage(), null, HttpStatus.MULTI_STATUS);
        }
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long userId) {

        try {
            userService.deleteUser(userId);
            return ResponseHandler.generateResponse(Constants.DELETED_SUCCESSFULLY, null, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(true, e.getMessage(), null, HttpStatus.MULTI_STATUS);
        }


    }


}
