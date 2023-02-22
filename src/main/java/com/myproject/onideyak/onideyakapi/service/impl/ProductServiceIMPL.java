package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.dto.paginated.PaginatedProductDTO;
import com.myproject.onideyak.onideyakapi.dto.paginated.conveter.ProductConverter;
import com.myproject.onideyak.onideyakapi.dto.request.ProductRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.request.ProductUpdateRequestDTO;
import com.myproject.onideyak.onideyakapi.dto.response.CommonResponseDTO;
import com.myproject.onideyak.onideyakapi.dto.response.ProductResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Product;
import com.myproject.onideyak.onideyakapi.exception.ConflictException;
import com.myproject.onideyak.onideyakapi.exception.NotFoundException;
import com.myproject.onideyak.onideyakapi.repo.ProductRepo;
import com.myproject.onideyak.onideyakapi.service.ProductService;
import com.myproject.onideyak.onideyakapi.util.Generator;
import com.myproject.onideyak.onideyakapi.util.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceIMPL implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final Generator generator;

    @Autowired
    public ProductServiceIMPL(ProductRepo productRepo, ProductMapper productMapper, Generator generator) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.generator = generator;
    }

    @Override
    public CommonResponseDTO createNewProduct(ProductRequestDTO productRequestDTO) {
        //id genarate using algorith
        // dto
        // maps truct use karala convert karanna entity
        // save
//        String primaryKey = generator.generatedPrimaryKey(generator.generatePrefix(5), "P", 5);

        Product product = new Product(
                generator.generatedPrimaryKey(generator.generatePrefix(5), "P", 5),
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
        return new CommonResponseDTO(200, "successfully saved ", product.getDisplayName());
    }

    @Override
    public CommonResponseDTO getProduct(String id) {
        Optional<Product> selectedProduct = productRepo.findByPropertyIdEquals(id);

        if (selectedProduct.isPresent()) {
            ProductResponseDTO productResponseDTO = productMapper.entityToResponseDto(selectedProduct.get());
            return new CommonResponseDTO(200, "Product Details", productResponseDTO);
        } else {
            throw new NotFoundException(id + " not in database");
        }
    }

    @Override
    public CommonResponseDTO updateProduct(ProductUpdateRequestDTO productUpdateRequestDTO, String productID) {
        // find product using id
        Optional<Product> selectedProduct = productRepo.findByPropertyIdEquals(productID);

        if (selectedProduct.isEmpty()) throw new NotFoundException(productID + " not in database");

        // update field
        selectedProduct.get().setDisplayName(productUpdateRequestDTO.getDisplayName());
        selectedProduct.get().setDescription(productUpdateRequestDTO.getDescription());
        selectedProduct.get().setUnitPrice(productUpdateRequestDTO.getUnitPrice());
        selectedProduct.get().setQty(productUpdateRequestDTO.getQty());
        selectedProduct.get().setSellingPrice(productUpdateRequestDTO.getSellingPrice());

        // save
        productRepo.save(selectedProduct.get());

        return new CommonResponseDTO(201, "Successfully updated!", null);
    }

    @Override
    public CommonResponseDTO deleteProduct(String productId) {
        Optional<Product> product = productRepo.findByPropertyIdEquals(productId);
        if (product.isEmpty()) throw new NotFoundException(productId + " not in database");
        productRepo.delete(product.get());
        return new CommonResponseDTO(200, "Successfully deleted!", null);
    }

    @Override
    public CommonResponseDTO findProductByName(String productName) {
        List<Product> productList = productRepo.findByName(productName);
        List<ProductResponseDTO> productResponseDTOList = productMapper.entityListToDtoList(productList);
        if (productList.isEmpty()) throw new NotFoundException(productName + " not in database");
        return new CommonResponseDTO(200, "products!", productResponseDTOList);
    }

    @Override
    public CommonResponseDTO findAll(String searchText, int page, int size) {
        List<ProductConverter> productConverterList = productRepo.findAllProductsWithPaginated(searchText, PageRequest.of(page, size));

        List<ProductResponseDTO> productResponseDTOList = productMapper.convertableListToDtoList(productConverterList);

        long dataCount = productRepo.getCount(searchText);

        return new CommonResponseDTO(200, "All product", new PaginatedProductDTO(dataCount, productResponseDTOList));
    }

    @Override
    public CommonResponseDTO getOutOfStock(int page, int size) {
        List<Product> productList = productRepo.getOutStockWithPaginated(PageRequest.of(page, size));
        if (productList.isEmpty()) throw new NotFoundException("All stocks are in the warehouse");

        long dataCount = productList.size();

        List<ProductResponseDTO> productResponseDTOList = productMapper.entityListToDtoList(productList);

        return new CommonResponseDTO(
                200,
                "Out of stock product List",
                new PaginatedProductDTO(dataCount, productResponseDTOList)
        );
    }

    @Override
    public CommonResponseDTO updateNewProductQty(int newQty, String productId) {
        Optional<Product> selectedProduct = productRepo.findByPropertyIdEquals(productId);

        if (selectedProduct.isEmpty()) throw new NotFoundException(productId + " not in database");

        if (newQty > 0) {
            selectedProduct.get().setQty(newQty);
            return new CommonResponseDTO(201, "updated new Qty", productId+" new QTY: "+newQty);
        }

        throw new ConflictException("QTY not accepted");

    }


}
