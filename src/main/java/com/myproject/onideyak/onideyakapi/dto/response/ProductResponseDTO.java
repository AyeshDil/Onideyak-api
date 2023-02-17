package com.myproject.onideyak.onideyakapi.dto.response;

import com.myproject.onideyak.onideyakapi.entity.ProductImages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDTO {
    private String propertyId;
    private String displayName;
    private String description;
    private double unitPrice;
    private int qty;
    private List<ProductImages> images;
    private double sellingPrice;
}
