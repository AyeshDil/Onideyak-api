package com.myproject.onideyak.onideyakapi.service;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;

public interface ProductService {
    CommonResponseDTO createNewProduct(ProductRequestDTO productRequestDTO);
}
