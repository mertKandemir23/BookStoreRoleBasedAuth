package com.kandemirmert.bookstoremanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddRoleToUserRequest {
    private String username;
    private String rolename;
}
