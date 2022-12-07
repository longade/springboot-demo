package com.longade.springdemo.service;

import com.longade.springdemo.domain.User;
import com.longade.springdemo.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    Optional<User> getUser(Long id);
    void saveUser(UserDTO userDTO);
    void deleteUser(Long userId);

}
