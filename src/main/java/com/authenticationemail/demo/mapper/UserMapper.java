package com.authenticationemail.demo.mapper;

import com.authenticationemail.demo.models.UserDTO;
import com.authenticationemail.demo.models.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);
}
