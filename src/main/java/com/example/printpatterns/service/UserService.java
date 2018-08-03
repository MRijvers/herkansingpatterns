package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
