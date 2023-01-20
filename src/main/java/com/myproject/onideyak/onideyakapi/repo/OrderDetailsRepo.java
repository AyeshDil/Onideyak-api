package com.myproject.onideyak.onideyakapi.repo;

import com.myproject.onideyak.onideyakapi.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, String> {
}
