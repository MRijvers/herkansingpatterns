package com.example.printpatterns.service.serviceimpl;

import  com.example.printpatterns.data.RoleRepository;
import  com.example.printpatterns.data.UserRepositories;
import  com.example.printpatterns.domain.entity.Role;
import  com.example.printpatterns.domain.entity.User;
import com.example.printpatterns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userDao;
    @Autowired
    private RoleRepository roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email)
    {
        return userDao.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleDao.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userDao.save(user);
    }
}
