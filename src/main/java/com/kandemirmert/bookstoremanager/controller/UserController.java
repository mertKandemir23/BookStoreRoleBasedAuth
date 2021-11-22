package com.kandemirmert.bookstoremanager.controller;

import com.kandemirmert.bookstoremanager.model.AddRoleToUserRequest;
import com.kandemirmert.bookstoremanager.model.Role;
import com.kandemirmert.bookstoremanager.model.User;
import com.kandemirmert.bookstoremanager.services.RoleService;
import com.kandemirmert.bookstoremanager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/userapi")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));

    }

    @PostMapping
    public ResponseEntity<User> createAnUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createAnuser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteAnUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/addrole")
    public ResponseEntity<Void> addRoleToUser(@RequestBody AddRoleToUserRequest roleToUserRequest) {
        userService.addRoleToUser(roleToUserRequest);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    public ResponseEntity<Void> changeARole(String username, String newRolename, String oldRolename) {
        userService.changeARole(username, newRolename, oldRolename);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/role")
    public ResponseEntity<Role> saveARole(@RequestBody Role role) {
        userService.saveARole(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/roles")
    public ResponseEntity<Void> deleteAllRoles() {
        roleService.deleteAllRoles();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}