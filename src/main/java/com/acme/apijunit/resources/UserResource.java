package com.acme.apijunit.resources;

import com.acme.apijunit.domain.User;
import com.acme.apijunit.domain.dto.UserDTO;
import com.acme.apijunit.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findbyId(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));

    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        List<UserDTO> listDTO = userService.findAll()
                .stream().map(user -> mapper.map(user, UserDTO.class)).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj){
        User newObj = userService.create(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj){
        obj.setId(id);
        User newObj = userService.update(obj);
        return ResponseEntity.ok().body(mapper.map(newObj, UserDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
