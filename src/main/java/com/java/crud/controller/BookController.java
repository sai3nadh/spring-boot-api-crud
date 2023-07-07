package com.java.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.java.crud.entity.Book;
import com.java.crud.service.BookService;

@RestController
public class BookController {

	 @Autowired
	    private BookService service;

	    @PostMapping("/addBook")
	    public Book addBook(@RequestBody Book book) {
	        return service.saveBook(book);
	    }

		
		  @PostMapping("/addBooks")
		  public List<Book> addBooks(@RequestBody List<Book> books) {
			  return service.saveBooks(books); }
		 
	    

		

	    @GetMapping("/books")
	    public List<Book> findAllBooks() {
	        return service.getBooks();
	    }

	    @GetMapping("/book/{id}")
	    public Book findBookById(@PathVariable int id) {
	        return service.getBookById(id);
	    }
		
	    
	    // methods with return status codes and basic custom message
	    
	    @GetMapping("/allbooks")
	    public ResponseEntity<List<Book>> findAllBookss() {
	        List<Book> books = service.getBooks();
	        //books.
	        return new ResponseEntity<>(books, HttpStatus.OK);
	    }
	    
	    @GetMapping("/bookid/{id}")
	    public ResponseEntity<Book> findBookById1(@PathVariable int id) {
	        try {
	            Book book = service.getBookById(id);
	            if(book != null) {
	            return new ResponseEntity<>(book, HttpStatus.OK);
	            }
	            else {
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found") ;
	            }
	        } catch (IllegalArgumentException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found", e);
	        }
	    }
	    
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {

	    	return new ResponseEntity<>(ex.getMessage()+"-->custom handle exception", HttpStatus.INTERNAL_SERVER_ERROR);
	        
	    }
	  
		
		  @ExceptionHandler(Exception.class) public ResponseEntity<String>
		  handleException(Exception ex,String m) {
		  
		  return new ResponseEntity<>(ex.getMessage()+"-->custom handle exception1 "+m,
		  HttpStatus.INTERNAL_SERVER_ERROR);
		  
		  }
		 
}
