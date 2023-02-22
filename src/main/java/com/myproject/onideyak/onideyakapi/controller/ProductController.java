package com.myproject.onideyak.onideyakapi.controller;

import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.ProductUpdateRequestDTO;
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
    ) {
        CommonResponseDTO commonResponseDTO = productService.createNewProduct(productRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.CREATED
        );
    }


    @GetMapping("/member/get/{id}")
    public ResponseEntity<StandardResponse> getProductDetails(
            @PathVariable(value = "id") String id
    ) {
        CommonResponseDTO commonResponseDTO = productService.getProduct(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.FOUND
        );
    }

    @PutMapping(value = {"/member/update"}, params = {"productId"})
    public ResponseEntity<StandardResponse> updateProductDetails(
            @RequestBody ProductUpdateRequestDTO productUpdateRequestDTO,
            @RequestParam(value = "productId") String productID
    ) {
        CommonResponseDTO commonResponseDTO = productService.updateProduct(productUpdateRequestDTO, productID);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(value = {"/member/remove/{productId}"})
    public ResponseEntity<StandardResponse> deleteProduct(@PathVariable(value = "productId") String productId) {
        CommonResponseDTO commonResponseDTO = productService.deleteProduct(productId);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );
    }

    @GetMapping(value = {"/member/find-by-name/{productName}"})
    public ResponseEntity<StandardResponse> findProductByUsingName(@PathVariable(value = "productName") String productName) {
        CommonResponseDTO commonResponseDTO = productService.findProductByName(productName);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );

    }

    @GetMapping(value = {"/member/list"},
            params = {"searchText", "page", "size"}
    )
    public ResponseEntity<StandardResponse> findProductByUsingName(
            @RequestParam(required = false) String searchText,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size

    ) {
        CommonResponseDTO commonResponseDTO = productService.findAll(searchText, page, size);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );

    }

    @GetMapping(
            value = {"/member/out-of-stock"},
            params = {"page", "size"}
    )
    private ResponseEntity<StandardResponse> getOutOfStocksProduct(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        CommonResponseDTO commonResponseDTO = productService.getOutOfStock(page, size);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );
    }

    @PutMapping(
            value = {"/member/add-new-stock/{id}"},
            params = {"qty"}
    )
    public ResponseEntity<StandardResponse> updateNewQty(
            @PathVariable(value = "id") String productId,
            @RequestParam(value = "qty") int newQty
    ) {
        CommonResponseDTO commonResponseDTO = productService.updateNewProductQty(newQty, productId);

        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.OK
        );
    }

}
