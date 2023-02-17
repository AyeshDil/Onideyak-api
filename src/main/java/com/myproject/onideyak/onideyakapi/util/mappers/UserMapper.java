package com.myproject.onideyak.onideyakapi.util.mappers;

import com.myproject.onideyak.onideyakapi.dto.request.UserRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.UserUpdateRequestDTO;
import com.myproject.onideyak.onideyakapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserRequestDTO userRequestDTO);

    User updatedDtoToEntity(UserUpdateRequestDTO userUpdateRequestDTO);
}
