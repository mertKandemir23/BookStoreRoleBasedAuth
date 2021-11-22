package com.kandemirmert.bookstoremanager.repository;

import com.kandemirmert.bookstoremanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

        Role findByName(String roleName);
        void deleteById(Long id);
    }
