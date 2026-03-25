package com.satva.satvafoods.service;

import com.satva.satvafoods.entity.User;
import com.satva.satvafoods.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER USER
    public User registerUser(User user) {

        if(user.getName() == null || user.getName().isEmpty()){
            throw new RuntimeException("Name cannot be empty");
        }

        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new RuntimeException("Email cannot be empty");
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        if(user.getPassword() == null || user.getPassword().length() < 5){
            throw new RuntimeException("Password must be at least 5 characters");
        }

        // ROLE LOGIC
        if(user.getEmail().equalsIgnoreCase("admin@satva.com")){
            user.setRole("ADMIN");
        } else {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // LOGIN USER
    public User loginUser(String email, String password){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    // GET USER PROFILE
    public User getUserById(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE USER PROFILE
    public User updateUser(int id, User updatedUser){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(updatedUser.getName()!=null){
            user.setName(updatedUser.getName());
        }

        if(updatedUser.getEmail()!=null){
            user.setEmail(updatedUser.getEmail());
        }

        if(updatedUser.getPhone()!=null){
            user.setPhone(updatedUser.getPhone());
        }

        if(updatedUser.getAddress()!=null){
            user.setAddress(updatedUser.getAddress());
        }

        return userRepository.save(user);
    }
}