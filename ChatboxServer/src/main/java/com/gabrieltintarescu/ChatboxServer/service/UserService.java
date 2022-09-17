package com.gabrieltintarescu.ChatboxServer.service;

import com.gabrieltintarescu.ChatboxServer.exception.errors.UserAlreadyExistsException;
import com.gabrieltintarescu.ChatboxServer.model.Role;
import com.gabrieltintarescu.ChatboxServer.model.User;

import java.util.List;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public interface UserService {
    User saveUser(User user) throws UserAlreadyExistsException;
    void addRoleToUser( Role role, String username);
    List<User> getUsers();
}
