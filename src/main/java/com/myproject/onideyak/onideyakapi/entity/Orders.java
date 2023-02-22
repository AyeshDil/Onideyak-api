package com.myproject.onideyak.onideyakapi.entity;

import com.myproject.onideyak.onideyakapi.enums.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "order_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {
    @Id
    @Column(name = "property_id", length = 200)
    private String propertyId;

    @Column(name = "date")
    private Date date;

    @Column(name = "total_cost")
    private double totalCost;

    @Column(name = "shipping_address", length = 750)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "user_property_id")
    private User userPropertyId;

    @OneToMany(mappedBy = "ordersPropertyId")
    private Set<OrderDetails> orderDetails;

    @OneToOne(mappedBy = "ordersPropertyId" )
    private Payment payment;
}
