package com.myproject.onideyak.onideyakapi.repo;

import com.myproject.onideyak.onideyakapi.entity.User;
import com.myproject.onideyak.onideyakapi.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User, String> {
    List<User> findUserByUserRole(UserRole userRole);

    Optional<User> findByPrefix(String generatedPrefix);

    Optional<User> findByEmailEquals(String email);
}
