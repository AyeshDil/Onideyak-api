package com.myproject.onideyak.onideyakapi.util.mappers;

import com.myproject.onideyak.onideyakapi.dto.paginated.conveter.ProductConverter;
import com.myproject.onideyak.onideyakapi.dto.response.ProductResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO entityToResponseDto(Product product);

    List<ProductResponseDTO> entityListToDtoList(List<Product> productList);

    List<ProductResponseDTO> convertableListToDtoList(List<ProductConverter> productConverterList);
}
