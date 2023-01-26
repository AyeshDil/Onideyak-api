package com.myproject.onideyak.onideyakapi.service.impl;

import com.myproject.onideyak.onideyakapi.entity.User;
import com.myproject.onideyak.onideyakapi.entity.UserRole;
import com.myproject.onideyak.onideyakapi.entity.share.UserNameResource;
import com.myproject.onideyak.onideyakapi.repo.UserRepo;
import com.myproject.onideyak.onideyakapi.repo.UserRoleRepo;
import com.myproject.onideyak.onideyakapi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceIMPL implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void initializeRoles() {

        // save user roles
        List<UserRole> userRoles = userRoleRepo.findAll();
        UserRole adminRole = null;

        if (userRoles.isEmpty()) {
            UserRole role1 = new UserRole("UR-1", "ADMIN", "Super Privileges", null);
            UserRole role2 = new UserRole("UR-2", "MANAGER", "Management Privileges", null);
            UserRole role3 = new UserRole("UR-3", "USER", "Regular USer", null);

            userRoleRepo.saveAll(List.of(role1, role2, role3));
            adminRole = role1;
        }

        // save default user (Must be a ADMIN)

        List<User> admins = userRepo.findUserByUserRole("UR-1");
        if (admins.isEmpty()){
            User user = new User(
                    "",
                    "011",
                    new UserNameResource("Ayesh", "Dilshan"),
                    "dilshanwma@gmail.com",
                    "1234",
                    true,
                    true,
                    true,
                    true,
                    "generated value",
                    null,
                    null,
                    null,
                    adminRole
            );
        }


    }
}
