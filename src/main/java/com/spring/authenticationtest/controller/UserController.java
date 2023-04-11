package com.spring.authenticationtest.controller;

import com.spring.authenticationtest.model.User;
import com.spring.authenticationtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
//@PreAuthorize("hasRole('USER')")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String addUser(@RequestParam String username, @RequestParam String password) {
        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);
        return "Added new user to repo!";
    }

    @GetMapping("/list")
    public Iterable<User> getCustomers() {

        return userRepository.findAll();
    }

    @GetMapping("/{username}")
    public Optional<User> findCustomerByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
