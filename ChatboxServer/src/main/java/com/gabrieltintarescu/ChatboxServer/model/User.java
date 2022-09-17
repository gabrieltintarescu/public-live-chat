package com.gabrieltintarescu.ChatboxServer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name cannot be empty.")
    private String name;
    @Column(unique=true)
    @NotEmpty(message = "Email cannot be empty.")
    @Email(message = "Email should be valid.")
    private String email;
    @Column(unique=true)
    @NotEmpty(message = "Username cannot be empty.")
    private String username;
    @NotEmpty(message = "Username cannot be empty.")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
