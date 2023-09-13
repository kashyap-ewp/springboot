package com.example.httpclientdemo.mapper;

import com.example.httpclientdemo.models.User;
import com.example.httpclientdemo.models.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO(User user);

    List<User> toUsers(List<UserDTO> userDTOS);
    List<UserDTO> toUserDTOs(List<User> users);

    User toUser(UserDTO userDTO);
}
