package com.myproject.onideyak.onideyakapi.entity;


import com.myproject.onideyak.onideyakapi.entity.share.FileResource;
import com.myproject.onideyak.onideyakapi.entity.share.UserNameResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Column(name = "property_id", length = 200)
    private String propertyId;

    @Column(name = "contact_number", length = 45)
    private String contactNumber;

    @Embedded
    private UserNameResource userName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 750)
    private String password;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "prefix", length = 16)
    private String prefix;

    @Embedded
    private FileResource avatar;

    @OneToMany(mappedBy = "userPropertyId", cascade = CascadeType.ALL)
    private List<Orders> ordersList;

    @OneToMany(mappedBy = "userPropertyId", cascade = CascadeType.ALL)
    private Set<LovedProperty> lovedProperties;
}

