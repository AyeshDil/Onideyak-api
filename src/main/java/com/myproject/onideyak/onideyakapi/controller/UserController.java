package com.myproject.onideyak.onideyakapi.controller;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.service.UserService;
import com.myproject.onideyak.onideyakapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/visitor/register")
    public ResponseEntity<StandardResponse> createUser(@RequestBody UserRequestDTO userRequestDTO) throws MessagingException {
       CommonResponseDTO commonResponseDTO = userService.createUser(userRequestDTO);
       return null;
    }

}
