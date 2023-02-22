package com.myproject.onideyak.onideyakapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "product_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "property_id", length = 200)
    private String propertyId;

   /* @Column(name = "display_id", length = 200)
    private String displayId;*/

    @Column(name = "display_name", length = 150)
    private String displayName;

    @Column(name = "description", length = 750)
    private String description;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "qty")
    private int qty;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImages> images;

    @Column(name = "selling_price")
    private double sellingPrice;

    @OneToMany(mappedBy = "productPropertyId")
    private Set<OrderDetails> orderDetails;

    @OneToMany(mappedBy = "productPropertyId", cascade = CascadeType.ALL)
    private Set<LovedProperty> lovedProperties;

}

