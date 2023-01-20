package com.myproject.onideyak.onideyakapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;

import javax.persistence.*;

@Entity(name = "order_details_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    @Id
    @Column(name = "property_id", length = 200)
    private String propertyId;

    @ManyToOne
    @JoinColumn(name = "orders_property_id")
    private Orders ordersPropertyId;

    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private Product productPropertyId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "qty")
    private int qty;

    @Column(name = "unit_price")
    private double unitPrice;

}


