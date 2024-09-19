package com.implementation.security.repository;

import com.implementation.security.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, String> {

    Optional<Roles> findByName(String name);
}
