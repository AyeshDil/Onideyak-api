package com.myproject.onideyak.onideyakapi.controller;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.service.ProductService;
import com.myproject.onideyak.onideyakapi.util.StandardResponse;
import com.myproject.onideyak.onideyakapi.util.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/member/create")
    public ResponseEntity<StandardResponse> createProduct(
            @RequestBody ProductRequestDTO productRequestDTO
            ){
        CommonResponseDTO commonResponseDTO = productService.createNewProduct(productRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()), HttpStatus.CREATED
                );
    }


    @GetMapping("/member/get/{id}")
    public ResponseEntity<StandardResponse> getProductDetails(
            @PathVariable(value = "id") String id
    ){
        CommonResponseDTO commonResponseDTO = productService.getProduct(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()), HttpStatus.FOUND
        );
    }


}
