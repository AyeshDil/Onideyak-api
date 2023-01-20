package com.myproject.onideyak.onideyakapi.entity.share;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class UserNameResource {
    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;
}
