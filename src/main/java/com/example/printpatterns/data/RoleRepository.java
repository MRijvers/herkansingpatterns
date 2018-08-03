package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
