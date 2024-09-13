package com.implementation.security.repository;

import com.implementation.security.entity.Roles;
import com.implementation.security.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<Users, String> {
    
}
