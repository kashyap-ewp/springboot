package com.example.httpclientdemo.controllers;

import com.example.httpclientdemo.mapper.UserMapper;
import com.example.httpclientdemo.models.User;
import com.example.httpclientdemo.models.UserDTO;
import com.example.httpclientdemo.models.ResponseDTO;
import com.example.httpclientdemo.services.UserService;
import com.example.httpclientdemo.services.UserWebService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserWebService userWebService;
    @Autowired
    UserMapper productMapper;

    @GetMapping("/rt")
    public String findAllRT()
    {
        return userWebService.findAllRT();
    }

    @GetMapping("/callMicro")
    public Mono<String> findAllWC()
    {
        return userWebService.findAllWC();
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@RequestBody @Valid UserDTO userDTO)
    {
        User c = userService.insert(productMapper.toUser(userDTO));

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .message("Data Saved")
                .data(productMapper.toUserDTO(c))
                .build();

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updated(@RequestBody @Valid UserDTO userDTO)
    {
        User c = userService.insert(productMapper.toUser(userDTO));

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .message("Data Saved")
                .data(productMapper.toUserDTO(c))
                .build();

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PostMapping("/insertAll")
    public ResponseEntity<ResponseDTO> insertAll(@RequestBody List<@Valid UserDTO> userDTOS)
    {
        List<User> cities = userService.insertMultiple(productMapper.toUsers(userDTOS));

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .message("Data Saved")
                .data(productMapper.toUserDTOs(cities))
                .build();

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDTO> findAll()
    {
        List<User> cities = userService.findAll();

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .data(productMapper.toUserDTOs(cities))
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable int id)
    {
        User product = userService.findById(id);

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .data(productMapper.toUserDTO(product))
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id)
    {
        userService.deleteById(id);

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .message("Data deleted")
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @DeleteMapping("/deleteAllById")
    public ResponseEntity<ResponseDTO> deleteAllById(@RequestBody List<Integer> ids)
    {
        userService.deleteAllById(ids);

        ResponseDTO res = ResponseDTO.builder()
                .status(true)
                .message("Data deleted")
                .build();

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
