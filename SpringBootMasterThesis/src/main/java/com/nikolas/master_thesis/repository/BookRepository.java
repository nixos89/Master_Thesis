package com.nikolas.master_thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikolas.master_thesis.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
