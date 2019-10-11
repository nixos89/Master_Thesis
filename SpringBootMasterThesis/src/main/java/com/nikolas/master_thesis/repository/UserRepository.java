package com.nikolas.master_thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolas.master_thesis.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
