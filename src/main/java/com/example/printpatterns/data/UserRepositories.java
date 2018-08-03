package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
