package com.myproject.onideyak.onideyakapi.service;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;

import javax.mail.MessagingException;

public interface UserService {
    CommonResponseDTO createUser(UserRequestDTO userRequestDTO) throws MessagingException;
}
