package com.myproject.onideyak.onideyakapi;

import com.myproject.onideyak.onideyakapi.entity.UserRole;
import com.myproject.onideyak.onideyakapi.repo.UserRoleRepo;
import com.myproject.onideyak.onideyakapi.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnideyakApiApplication implements CommandLineRunner {

	@Autowired
	private UserRoleService userRoleService;

	public static void main(String[] args) {
		SpringApplication.run(OnideyakApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRoleService.initializeRoles();

	}
}
