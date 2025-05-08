package com.webservices.restful_web_services.controller;

import com.webservices.restful_web_services.exception.UserNotFoundException;
import com.webservices.restful_web_services.model.Post;
import com.webservices.restful_web_services.model.User;
import com.webservices.restful_web_services.repository.UserRepository;
import com.webservices.restful_web_services.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResourceJPA {

    private UserRepository repository;

    public UserResourceJPA(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users-jpa")
    public List<User> retrievAllUsers()
    {
        return repository.findAll();
    }


    @GetMapping("/users-jpa/{id}")
    public User retrievUserById(@PathVariable int id)
    {
       User user= repository.findById(id).get();
        if(user==null)
            throw new UserNotFoundException("id :"+id);
        return user;
    }

    @PostMapping("/users-jpa")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
      User savedUser =  repository.save(user);

        //  /user/{id} -> user.getId()
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //return url of created resource
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users-jpa/{id}")
    public void deleteUserById(@PathVariable int id)
    {
        repository.deleteById(id);
    }


}
