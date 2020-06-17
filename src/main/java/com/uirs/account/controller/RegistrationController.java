package com.uirs.account.controller;

import com.uirs.account.dto.RegistrationRequestDTO;
import com.uirs.account.entity.User;
import com.uirs.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/reg/")
public class RegistrationController {


    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity register(@RequestBody RegistrationRequestDTO requestDTO) {
        try {
            User newUser = new User(
                    requestDTO.getUsername(),
                    requestDTO.getFirstName(),
                    requestDTO.getLastName(),
                    requestDTO.getSecondName(),
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );

            userService.register(newUser);

            return ResponseEntity.ok("Successfully registered");
        }
        catch (AuthenticationException e) {
            throw new BadCredentialsException("Exception happened");
        }
    }

    @GetMapping("test")
    public ResponseEntity testPing() {
        return ResponseEntity.ok("4etko");
    }
}
