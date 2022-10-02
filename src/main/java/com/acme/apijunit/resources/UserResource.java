package com.acme.apijunit.resources;

import com.acme.apijunit.domain.User;
import com.acme.apijunit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findbyId(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.findById(id));

    }



}
