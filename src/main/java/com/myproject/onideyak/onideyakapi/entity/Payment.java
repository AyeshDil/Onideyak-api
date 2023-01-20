package com.myproject.onideyak.onideyakapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "payment_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @Column(name = "property_id", length = 200)
    private String propertyId;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "bank", length = 100)
    private String bank;

    @OneToOne
    @JoinColumn(name = "orders_property_id", unique = true)
    private Orders ordersPropertyId;

}
