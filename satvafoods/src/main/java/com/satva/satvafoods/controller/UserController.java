package com.satva.satvafoods.controller;

import com.satva.satvafoods.dto.LoginRequest;
import com.satva.satvafoods.entity.User;
import com.satva.satvafoods.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER USER
    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    // LOGIN USER
    @PostMapping("/login")
    public User loginUser(@RequestBody LoginRequest request){
        return userService.loginUser(request.getEmail(), request.getPassword());
    }

    // GET USER PROFILE
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    // UPDATE USER PROFILE
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser){
        return userService.updateUser(id, updatedUser);
    }

}