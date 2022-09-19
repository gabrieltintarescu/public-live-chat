package com.gabrieltintarescu.ChatboxServer.service;

import com.gabrieltintarescu.ChatboxServer.exception.errors.UserAlreadyExistsException;
import com.gabrieltintarescu.ChatboxServer.model.User;
import com.gabrieltintarescu.ChatboxServer.model.Role;
import com.gabrieltintarescu.ChatboxServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Gabriel Tintarescu
 * @project ChatboxServer
 * @created 9/16/2022
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Trying to fetch user either my username or email.
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = userRepository.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found.");
            }
        }

        // Transforming authorities strings to SimpleGrantedAuthorities
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        // Making sure user
        if(userRepository.findByUsername(user.getUsername()) != null ||
        userRepository.findByEmail(user.getEmail()) != null){
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void addRoleToUser(Role role, String username) {
        User user = userRepository.findByUsername(username);
        user.getRoles().add(role);
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void banUser(String username) {
        User user = userRepository.findByUsername(username);
        user.setBanned(true);
    }
}
