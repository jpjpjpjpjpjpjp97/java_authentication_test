package com.spring.authenticationtest.repository;

import com.spring.authenticationtest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

