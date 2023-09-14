package com.authenticationemail.demo.repositories;

import com.authenticationemail.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
