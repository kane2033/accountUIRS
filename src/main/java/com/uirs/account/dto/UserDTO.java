package com.uirs.account.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uirs.account.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String lastName;
    private String email;
    private LocalDate birthday;

/*    public static User fromDTOtoUser(UserDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        return user;
    }*/

    public static UserDTO fromUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthday(user.getBirthday());
        return userDTO;
    }
}
