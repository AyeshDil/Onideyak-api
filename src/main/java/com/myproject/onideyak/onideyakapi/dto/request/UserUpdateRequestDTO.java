package com.myproject.onideyak.onideyakapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateRequestDTO {
    private String contactNumber;
    private String firstName;
    private String lastName;
    private String password;
}
