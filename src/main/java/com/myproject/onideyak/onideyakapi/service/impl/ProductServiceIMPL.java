package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.dto.response.ProductResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Product;
import com.myproject.onideyak.onideyakapi.exception.NotFoundException;
import com.myproject.onideyak.onideyakapi.repo.ProductRepo;
import com.myproject.onideyak.onideyakapi.service.ProductService;
import com.myproject.onideyak.onideyakapi.util.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceIMPL implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceIMPL(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

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

    @Override
    public CommonResponseDTO getProduct(String id) {
        Optional<Product> selectedProduct = productRepo.findByPropertyIdEquals(id);

        if (selectedProduct.isPresent()){
            ProductResponseDTO productResponseDTO = productMapper.entityToResponseDto(selectedProduct.get());
            return new CommonResponseDTO(200,"Product Details",productResponseDTO);
        }else {
            throw new NotFoundException(id+" not in database");
        }
    }

}
