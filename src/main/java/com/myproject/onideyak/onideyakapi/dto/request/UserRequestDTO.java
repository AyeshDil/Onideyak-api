package com.myproject.onideyak.onideyakapi.dto.request;

import com.myproject.onideyak.onideyakapi.entity.LovedProperty;
import com.myproject.onideyak.onideyakapi.entity.Orders;
import com.myproject.onideyak.onideyakapi.entity.UserRole;
import com.myproject.onideyak.onideyakapi.entity.share.FileResource;
import com.myproject.onideyak.onideyakapi.entity.share.UserNameResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
    private String contactNumber;
    private UserNameResource userName;
    private String email;
    private String password;
}
