package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "Manage users in the system")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new user (ADMIN only)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.register(user);
    }

    @Operation(summary = "Find user by email")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/email/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }
}