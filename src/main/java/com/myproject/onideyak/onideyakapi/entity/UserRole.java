package com.myproject.onideyak.onideyakapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_role_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRole {
    @Id
    @Column(name = "property_id", length = 200)
    private String propertyId;

    @Column(name = "role_name", length = 50)
    private String roleName;

    @Column(name = "role_description", length = 255)
    private String roleDescription;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;
}
