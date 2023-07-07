package com.java.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.crud.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {




}
