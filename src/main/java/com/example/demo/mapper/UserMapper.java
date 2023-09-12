package com.example.demo.mapper;

import com.example.demo.models.User;
import com.example.demo.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO(User user);

    List<User> toUsers(List<UserDTO> userDTOS);
    List<UserDTO> toUserDTOs(List<User> users);

    User toUser(UserDTO userDTO);
}
