package com.myproject.onideyak.onideyakapi.dto.request;

import com.myproject.onideyak.onideyakapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDTO {
    private double totalCost;
    private String shippingAddress;
    private String userPropertyId;
}
