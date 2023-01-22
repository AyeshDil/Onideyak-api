package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Product;
import com.myproject.onideyak.onideyakapi.repo.ProductRepo;
import com.myproject.onideyak.onideyakapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceIMPL implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public CommonResponseDTO createNewProduct(ProductRequestDTO productRequestDTO) {
        //id genarate using algorith
        // dto
        // maps truct use karala convert karanna entity
        // save
        Product product = new Product(
                "1",
                productRequestDTO.getDisplayName(),
                productRequestDTO.getDescription(),
                productRequestDTO.getUnitPrice(),
                productRequestDTO.getQty(),
                null,
                productRequestDTO.getSellingPrice(),
                null,
                null
        );
        productRepo.save(product);
        return new CommonResponseDTO(200,"successfully saved ", product.getDisplayName());
    }

}
