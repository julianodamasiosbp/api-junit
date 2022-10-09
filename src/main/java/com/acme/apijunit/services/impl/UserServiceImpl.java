package com.acme.apijunit.services.impl;

import com.acme.apijunit.domain.User;
import com.acme.apijunit.domain.dto.UserDTO;
import com.acme.apijunit.repositories.UserRepository;
import com.acme.apijunit.services.UserService;
import com.acme.apijunit.services.exceptions.DataIntegratyViolationException;
import com.acme.apijunit.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado!"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO obj){
        userRepository.findByEmail(obj.getEmail()).ifPresent(
                (userFound) -> {
                    if (!userFound.getId().equals(obj.getId())) {
                        throw new DataIntegratyViolationException("Email já cadastrado no sistema!");
                    }
                }
        );
    }
}
