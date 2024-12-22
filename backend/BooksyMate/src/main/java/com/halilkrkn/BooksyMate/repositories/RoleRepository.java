package com.halilkrkn.BooksyMate.repositories;

import com.halilkrkn.BooksyMate.entities.concretes.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String role);
}