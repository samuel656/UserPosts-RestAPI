package com.webservices.restful_web_services.controller;

import com.webservices.restful_web_services.exception.UserNotFoundException;
import com.webservices.restful_web_services.model.User;
import com.webservices.restful_web_services.service.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrievAllUsers()
    {
        return service.findALl();
    }


    @GetMapping("/users/{id}")
    public User retrievUserById(@PathVariable int id)
    {
        User user= service.findById(id);
        if(user==null)
            throw new UserNotFoundException("id :"+id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
      User savedUser =  service.save(user);

        //  /user/{id} -> user.getId()
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        //return url of created resource
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable int id)
    {
        service.deleteById(id);
    }
}
