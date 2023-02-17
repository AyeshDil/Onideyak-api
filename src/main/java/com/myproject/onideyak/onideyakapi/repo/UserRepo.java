package com.myproject.onideyak.onideyakapi.repo;

import com.myproject.onideyak.onideyakapi.entity.User;
import com.myproject.onideyak.onideyakapi.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, String> {
    List<User> findUserByUserRole(UserRole userRole);

    Optional<User> findByPrefix(String generatedPrefix);

    Optional<User> findByEmailEquals(String email);

    @Modifying
    @Query(value = "update user_table set contact_number=?1, first_name=?2, last_name=?3, password=?4 where property_id=?5",
    nativeQuery = true
    )
    void updateUserDetails(String contactNumber, String firstName, String lastName, String password, String userId);
}
