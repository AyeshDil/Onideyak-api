package com.myproject.onideyak.onideyakapi.repo;

import com.myproject.onideyak.onideyakapi.dto.paginated.conveter.ProductConverter;
import com.myproject.onideyak.onideyakapi.dto.response.ProductResponseDTO;
import com.myproject.onideyak.onideyakapi.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    Optional<Product> findByPropertyIdEquals(String id);

    @Query(
            value = "select * from product_table where display_name like %?1%",
            nativeQuery = true
    )
    List<Product> findByName(String displayName);

    @Query(
            value = "select property_id as propertyId, description, display_name as displayName, qty, selling_price as sellingPrice, unit_price as unitPrice from product_table where display_name like %?1%",
            nativeQuery = true
    )
    List<ProductConverter> findAllProductsWithPaginated(String text, Pageable pageable);

    @Query(
            value = "select count(*) from product_table where display_name like %?1%",
            nativeQuery = true
    )
    long getCount(String searchText);

    @Query(
            value = "select * from product_table where qty=0",
            nativeQuery = true
    )
    List<Product> getOutStockWithPaginated(Pageable pageable);
}
