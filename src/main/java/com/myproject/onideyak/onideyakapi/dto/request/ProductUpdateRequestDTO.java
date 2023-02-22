package com.myproject.onideyak.onideyakapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdateRequestDTO {
    private String displayName;
    private String description;
    private double unitPrice;
    private int qty;
    private double sellingPrice;
}
