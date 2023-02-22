package com.myproject.onideyak.onideyakapi.service;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.ProductUpdateRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;

public interface ProductService {
    CommonResponseDTO createNewProduct(ProductRequestDTO productRequestDTO);

    CommonResponseDTO getProduct(String id);

    CommonResponseDTO updateProduct(ProductUpdateRequestDTO productUpdateRequestDTO, String productID);

    CommonResponseDTO deleteProduct(String productId);

    CommonResponseDTO findProductByName(String productName);

    CommonResponseDTO findAll(String searchText, int page, int size);

    CommonResponseDTO getOutOfStock(int page, int size);

    CommonResponseDTO updateNewProductQty(int newQty, String productId);
}
