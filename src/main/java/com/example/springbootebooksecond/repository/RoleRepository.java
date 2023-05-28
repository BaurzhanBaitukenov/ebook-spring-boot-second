package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
