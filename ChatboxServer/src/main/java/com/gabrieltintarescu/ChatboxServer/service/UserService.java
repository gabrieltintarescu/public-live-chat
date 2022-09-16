package com.gabrieltintarescu.ChatboxServer.service;

import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.model.Role;

import java.util.List;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public interface UserService {
    User saveUser(User user);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
