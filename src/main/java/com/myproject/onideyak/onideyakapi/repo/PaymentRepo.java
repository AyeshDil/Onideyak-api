package com.myproject.onideyak.onideyakapi.repo;

import com.myproject.onideyak.onideyakapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface PaymentRepo extends JpaRepository<Payment, String> {
}
