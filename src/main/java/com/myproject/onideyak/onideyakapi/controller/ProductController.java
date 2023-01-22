package com.myproject.onideyak.onideyakapi.controller;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.service.ProductService;
import com.myproject.onideyak.onideyakapi.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

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

}
