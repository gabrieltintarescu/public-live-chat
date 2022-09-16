package com.gabrieltintarescu.ChatboxServer.repository;

import com.gabrieltintarescu.ChatboxServer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
