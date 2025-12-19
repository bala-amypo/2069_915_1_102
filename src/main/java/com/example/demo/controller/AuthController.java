// //AuthController.java
// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody User user) {
//         return ResponseEntity.ok(userService.register(user));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<User> login(@RequestBody User user) {
//         // Simple login: find user by email, compare password (no hashing/auth here as requested)
//         User existing = userService.findByEmail(user.getEmail());
//         if (!existing.getPassword().equals(user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }
//         return ResponseEntity.ok(existing);
//     }
// }

// src/main/java/com/example/demo/controller/AuthController.java
package com.example.demo.controller;

public class AuthController { }