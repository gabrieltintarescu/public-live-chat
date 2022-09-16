package com.gabrieltintarescu.ChatboxServer.repository;

import com.gabrieltintarescu.ChatboxServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
