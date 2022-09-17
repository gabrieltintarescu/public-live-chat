package com.gabrieltintarescu.ChatboxServer.controller;

import com.gabrieltintarescu.ChatboxServer.exception.errors.ResourceNotFoundException;
import com.gabrieltintarescu.ChatboxServer.exception.errors.UserAlreadyExistsException;
import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        return userService.saveUser(user);
    }

}
