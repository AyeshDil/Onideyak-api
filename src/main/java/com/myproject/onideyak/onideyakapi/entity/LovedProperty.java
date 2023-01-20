package com.myproject.onideyak.onideyakapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "loved_property_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LovedProperty {
    @Id
    @Column(name = "property_id",length = 200)
    private String propertyId;

    @Column(name = "saved_date")
    private Date savedDate;

    @ManyToOne
    @JoinColumn(name = "user_property_id")
    private User userPropertyId;

    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private Product productPropertyId;

}

