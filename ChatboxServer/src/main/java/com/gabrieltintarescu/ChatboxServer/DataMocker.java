package com.gabrieltintarescu.ChatboxServer;

import com.gabrieltintarescu.ChatboxServer.model.Role;
import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.service.RoleService;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@Component
public class DataMocker implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataMocker(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.saveRole(new Role(null, "ROLE_USER"));
        roleService.saveRole(new Role(null, "ROLE_MODERATOR"));
        roleService.saveRole(new Role(null, "ROLE_ADMIN"));

        userService.saveUser(new User(null, "Gabriel Tintarescu", "gabriel_tintarescu@yahoo.com", "gabi", "1234", new ArrayList<>()));
        userService.saveUser(new User(null, "Teodor Nicolau", "teodor@yahoo.com", "teo", "1234", new ArrayList<>()));

        userService.addRoleToUser("gabi", "ROLE_USER");
        userService.addRoleToUser("gabi", "ROLE_MODERATOR");
        userService.addRoleToUser("gabi", "ROLE_ADMIN");
        userService.addRoleToUser("teo", "ROLE_USER");


    }
}
