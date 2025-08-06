package com.example.userservice.user_service.controller;

import com.example.userservice.user_service.model.User;
import com.example.userservice.user_service.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/hello")
    String helloStudent(){
        return "Hello Books";

    }


    // GET all user
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return   userRepository.findAll();
    }

    //POST new User
    @PostMapping
    public  User createUser(@RequestBody User user){
        return   userRepository.save(user);
    }
    // GET User by ID
    @GetMapping("/{id}")
    public ResponseEntity <User> getUserById(@PathVariable Long id){
        return   userRepository.findById(id)
                .map(user  ->ResponseEntity.ok(user)).
                orElse(ResponseEntity.notFound().build());

    }
    // PUT update user
    // PUT update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateBook(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setPhone(userDetails.getPhone());
            user.setId(userDetails.getId());
            return ResponseEntity.ok(userRepository.save(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
