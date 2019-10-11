package com.nikolas.master_thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolas.master_thesis.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
