package com.kandemirmert.bookstoremanager.services;

import com.kandemirmert.bookstoremanager.model.AddRoleToUserRequest;
import com.kandemirmert.bookstoremanager.model.Role;
import com.kandemirmert.bookstoremanager.model.User;

import java.util.List;

public interface UserService {
    User createAnuser(User user);

    Role saveARole(Role role);

    void addRoleToUser(AddRoleToUserRequest roleToUserRequest);

    void deleteUser(String username);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void changeARole(String username, String newRolename, String oldRoleName);

    void deleteAllUsers();
}
