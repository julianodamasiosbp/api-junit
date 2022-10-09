package com.acme.apijunit.services;

import com.acme.apijunit.domain.User;
import com.acme.apijunit.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);


    List<User> findAll();

    User create(UserDTO obj);

    User update(UserDTO obj);

    void delete(Integer id);
}
