package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.register(user);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}
