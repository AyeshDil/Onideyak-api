package com.myproject.onideyak.onideyakapi.controller;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.UserUpdateRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.service.UserService;
import com.myproject.onideyak.onideyakapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = {"/visitor/register"})
    public ResponseEntity<StandardResponse> createUser(@RequestBody UserRequestDTO userRequestDTO) throws MessagingException {
        CommonResponseDTO commonResponseDTO = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.CREATED
        );
    }

    @PostMapping(
            value = {"/visitor/verify/{otp}"},
            params = {"email"})
    public ResponseEntity<StandardResponse> verifyUser(
            @PathVariable(value = "otp") String otp,
            @RequestParam String email
    ) {
        CommonResponseDTO commonResponseDTO = userService.verifyUser(otp, email);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.CREATED

        );
    }

    @PutMapping(value = {"/user/update"}, params = {"userId"})
    public ResponseEntity<StandardResponse> updateUser(
            @RequestBody UserUpdateRequestDTO userUpdateRequestDTO,
            @RequestParam(value = "userId") String userId){
        CommonResponseDTO commonResponseDTO = userService.updateUserDetails(userUpdateRequestDTO, userId);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                        ), HttpStatus.OK
        );
    }

    @PutMapping(value = {"/visitor/forgot-password"},
    params = {"email"})
    public ResponseEntity<StandardResponse> forgotPassword(@RequestParam(value = "email") String email) throws MessagingException {
        CommonResponseDTO commonResponseDTO = userService.forgotPassword(email);
        return new ResponseEntity<>(
                new StandardResponse(
                   commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.TEMPORARY_REDIRECT
        );
    }

    @PutMapping(value = {"/visitor/restart-password/{otp}"},
            params = {"email","newPassword"}
    )
    public ResponseEntity<StandardResponse> restartPassword(
            @PathVariable(value = "otp") String otp,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "newPassword") String newPassword
    ){
        CommonResponseDTO commonResponseDTO = userService.restartPassword(email, otp, newPassword);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.PERMANENT_REDIRECT
        );
    }

}
