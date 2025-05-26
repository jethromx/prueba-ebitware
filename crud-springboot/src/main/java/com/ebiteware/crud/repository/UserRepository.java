package com.ebiteware.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ebiteware.crud.entity.User;


//// UserRepository interface extends JpaRepository to provide CRUD operations for User entity.

public interface UserRepository extends JpaRepository<User, String> {

    // Custom query methods can be defined here if needed
    // For example, to find a user by name:
    // Optional<User> findByName(String name);
    
    // Additional methods can be added as per requirements
    Optional<User> findByEmail(String email);
    
}
