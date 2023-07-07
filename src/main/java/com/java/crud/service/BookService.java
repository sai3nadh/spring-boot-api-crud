package com.java.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.crud.entity.Book;
import com.java.crud.repository.BookRepository;

@Service
public class BookService {

	  @Autowired
	    private BookRepository repository;

	  //post methods
	    public Book saveBook(Book book) {
	        return repository.save(book);
	    }

	    public List<Book> saveBooks(List<Book> books) {
	        return repository.saveAll(books);
	    }
	    
	    //get methods

	    public List<Book> getBooks() {
	        return repository.findAll();
	    }

	    public Book getBookById(int id) {
	        return repository.findById(id).orElse(null);
	    }

		
}
