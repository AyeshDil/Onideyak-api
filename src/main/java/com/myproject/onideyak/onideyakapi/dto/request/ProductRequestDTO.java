package com.myproject.onideyak.onideyakapi.dto.request;

import com.myproject.onideyak.onideyakapi.entity.LovedProperty;
import com.myproject.onideyak.onideyakapi.entity.OrderDetails;
import com.myproject.onideyak.onideyakapi.entity.ProductImages;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDTO {

    private String displayName;
    private String description;
    private double unitPrice;
    private int qty;
    private double sellingPrice;

}

