package com.myproject.onideyak.onideyakapi.dto.paginated.conveter;

public interface ProductConverter {
    String getDisplayName();
    String getDescription();
    double getUnitPrice();
    int getQty();
    double getSellingPrice();
}
