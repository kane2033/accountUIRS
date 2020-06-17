package com.uirs.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uirs.account.entity.Status;
import com.uirs.account.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private LocalDate birthday;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));
        user.setBirthday(birthday);
        return user;
    }

    public static AdminUserDTO fromUser(User user) {
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        adminUserDTO.setId(user.getId());
        adminUserDTO.setUsername(user.getUsername());
        adminUserDTO.setFirstName(user.getFirstName());
        adminUserDTO.setLastName(user.getLastName());
        adminUserDTO.setEmail(user.getEmail());
        adminUserDTO.setStatus(user.getStatus().name());
        adminUserDTO.setBirthday(user.getBirthday());
        return adminUserDTO;
    }
}
