package com.gabrieltintarescu.ChatboxServer.service;

import com.gabrieltintarescu.ChatboxServer.model.Role;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public interface RoleService {
    Role saveRole(Role role);
    Role findByName(String roleName);

}
