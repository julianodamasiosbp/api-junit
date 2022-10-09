package com.acme.apijunit.config;

import com.acme.apijunit.domain.User;
import com.acme.apijunit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){
        User u1 = new User(null, "Fulano", "email@email", "234");
        User u2 = new User(null, "Pedro", "pedro@email", "555");

        userRepository.saveAll(List.of(u1, u2));
    }
}
