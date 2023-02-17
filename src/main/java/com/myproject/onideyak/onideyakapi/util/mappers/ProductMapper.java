package com.myproject.onideyak.onideyakapi.util.mappers;

import com.myproject.onideyak.onideyakapi.dto.response.ProductResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO entityToResponseDto(Product product);
}
