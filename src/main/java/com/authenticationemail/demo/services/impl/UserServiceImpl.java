package com.authenticationemail.demo.services.impl;

import com.authenticationemail.demo.exception.ApplicationRuntimeException;
import com.authenticationemail.demo.exception.UserNotLoggedInException;
import com.authenticationemail.demo.mapper.UserMapper;
import com.authenticationemail.demo.models.User;
import com.authenticationemail.demo.models.UserDTO;
import com.authenticationemail.demo.repositories.RoleRepository;
import com.authenticationemail.demo.repositories.UserRepository;
import com.authenticationemail.demo.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@SuppressWarnings("unused")
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails getLoggedInUser() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            return (UserDetails) userDetails;
        }

        throw  new UserNotLoggedInException();
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<User> user = UserRepository.findByUsername(username);
        if(user.isPresent()) return userMapper.toUserDTO(user.get());
        else throw new ApplicationRuntimeException("exception.user.usernameNotFound");
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        try {
            User user = userMapper.toUser(userDTO);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            //Call findAllByName with specific role name
            user.setRoles(new HashSet<>(roleRepository.findAll()));

            User userSaved = UserRepository.save(user);
            return userMapper.toUserDTO(userSaved);
        }
        catch(DataIntegrityViolationException dataIntegrityViolationException){
            log.info(dataIntegrityViolationException.toString());
            throw new ApplicationRuntimeException("exception.user.usernameExists");
        }
    }
}
