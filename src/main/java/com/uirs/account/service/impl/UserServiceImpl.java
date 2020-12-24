package com.uirs.account.service.impl;

import com.uirs.account.entity.Role;
import com.uirs.account.entity.Status;
import com.uirs.account.entity.User;
import com.uirs.account.exception.UserAlreadyExistsAuthenticationException;
import com.uirs.account.repository.RoleRepository;
import com.uirs.account.repository.UserRepository;
import com.uirs.account.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        String newUsername = user.getUsername();
        User existingUser = findByUsername(newUsername);

        if (existingUser != null) { //если такой пользователь уже есть
            throw new UserAlreadyExistsAuthenticationException("User with username " + newUsername + "already exists");
        }

        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword())); //кодировка пароля для хранения в бд
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register  - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }


    @Override
    //@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) { //если такого пользователя нет
            throw new UsernameNotFoundException("User with username " + username + "not found");
        }
        log.info("IN findByUsername - user {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}
