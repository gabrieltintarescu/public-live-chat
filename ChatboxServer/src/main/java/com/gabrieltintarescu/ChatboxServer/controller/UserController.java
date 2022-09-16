package com.gabrieltintarescu.ChatboxServer.controller;

import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/register")
    String registerUser(){
        return "Registering user!";
    }

}
