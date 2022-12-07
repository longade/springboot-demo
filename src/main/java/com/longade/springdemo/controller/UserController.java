package com.longade.springdemo.controller;

import com.longade.springdemo.domain.User;
import com.longade.springdemo.dto.UserDTO;
import com.longade.springdemo.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            LOGGER.info("Returning all users...");
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }
        catch (Exception e) {
            LOGGER.info("Error returning all users: ", e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        LOGGER.info("Saving user...");
        try {
            userService.saveUser(userDTO);
            LOGGER.info("User saved successfully!");
            return new ResponseEntity<>("User saved successfully!", HttpStatus.OK);
        }
        catch (Exception e) {
            LOGGER.error("Error saving user: ", e);
            return new ResponseEntity<>("Error saving user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) {
        LOGGER.info("Returning user with id: {}", userId);
        try {
            Optional<User> user = userService.getUser(userId);
            if (user.isEmpty()) {
                LOGGER.error("User not found");
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        catch (Exception e) {
            LOGGER.error("Error getting information for user with id: {}", userId);
            return new ResponseEntity<>("Error getting user info", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestParam Long userId) {
        LOGGER.info("Deleting user with id: {}", userId);
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
        }
        catch (Exception e) {
            LOGGER.error("Error deleting user");
            return new ResponseEntity<>("Error deleting user", HttpStatus.NOT_FOUND);
        }
    }

}
