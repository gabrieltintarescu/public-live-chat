package com.gabrieltintarescu.ChatboxServer.controller;

import com.gabrieltintarescu.ChatboxServer.model.Role;
import com.gabrieltintarescu.ChatboxServer.service.RoleService;
import com.gabrieltintarescu.ChatboxServer.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/17/2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role/")
public class RoleController {

    private final UserService userService;
    private final RoleService roleService;


    @PostMapping("/save")
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        Role role = roleService.findByName(form.getRoleName());
        userService.addRoleToUser(role, form.getUserName());
        return ResponseEntity.ok().build();
    }

    @Data
    private class RoleToUserForm {
        private String userName;
        private String roleName;
    }
}
