package com.jaga.usermanagement.controller;

import com.jaga.usermanagement.entity.User;
import com.jaga.usermanagement.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public HttpEntity<List<User>> getUsers() {
        return ResponseEntity.ok(this.userService.getUsers());
    }

    @GetMapping("/{id}")
    public HttpEntity<User> findUser(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findUser(id));
    }

    @PostMapping
    public HttpEntity createUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @PutMapping
    public HttpEntity updateUser(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Long id) {
        this.userService.removeUser(id);
    }

    @GetMapping("/search")
    public HttpEntity<List<User>> searchUsers(@RequestParam String name) {
        return ResponseEntity.ok(this.userService.searchUsers(name));
    }

    @PutMapping("/{id}/password")
    public HttpEntity<String> resetPassword(@PathVariable Long id, @RequestBody String newPassword) {
        this.userService.resetPassword(id, newPassword);
        return ResponseEntity.ok("Password reset done. Total resets: " + this.userService.getPasswordResetCount());
    }

}
