package com.uirs.account.controller;


import com.uirs.account.dto.UserDTO;
import com.uirs.account.dto.UserRequestDTO;
import com.uirs.account.entity.User;
import com.uirs.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/profile/")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

/*    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = UserDTO.fromUserToDTO(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/

    @GetMapping(path = "")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username){
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = UserDTO.fromUserToDTO(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "update", consumes={"application/json"})
    public ResponseEntity updateUser(@RequestBody UserRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        User user = userService.findByUsername(username);

        if (user == null) { //если такого пользователя нет
            throw new UsernameNotFoundException("User with username " + username + "not found");
        }

        requestDTO.updateUser(user);
        userService.save(user);

        return ResponseEntity.ok("Successfully updated user with username: " + username);
    }
}
