package com.uirs.account.controller;

import com.uirs.account.dto.AuthenticationRequestDTO;
import com.uirs.account.dto.AuthenticationResponseDTO;
import com.uirs.account.entity.User;
import com.uirs.account.security.jwt.JwtTokenProvider;
import com.uirs.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            String username = requestDTO.getUsername();
            //дается аунтиф-я на основе отправленного логина и пароля
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) { //если такого пользователя нет
                throw new UsernameNotFoundException("User with username " + username + "not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles()); //генерация токена

            AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO(username, token);

            return ResponseEntity.ok(responseDTO);
        }
        catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
