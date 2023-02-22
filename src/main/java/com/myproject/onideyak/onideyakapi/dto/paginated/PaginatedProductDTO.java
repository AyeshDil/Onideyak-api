package com.myproject.onideyak.onideyakapi.dto.paginated;

import com.myproject.onideyak.onideyakapi.dto.response.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedProductDTO {
    private long count;
    private List<ProductResponseDTO> data;


}
