package com.myproject.onideyak.onideyakapi.service;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.UserUpdateRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;

import javax.mail.MessagingException;

public interface UserService {
    CommonResponseDTO createUser(UserRequestDTO userRequestDTO) throws MessagingException;

    CommonResponseDTO verifyUser(String otp, String email);

    CommonResponseDTO updateUserDetails(UserUpdateRequestDTO userUpdateRequestDTO, String userId);

    CommonResponseDTO restartPassword(String email, String otp, String newPassword);

    CommonResponseDTO forgotPassword(String email) throws MessagingException;
}
