package com.acme.apijunit.services.impl;

import com.acme.apijunit.domain.User;
import com.acme.apijunit.repositories.UserRepository;
import com.acme.apijunit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }




}
