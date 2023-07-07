package com.java.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_TBL")
public class Book {
	
	
	  	@Id
	    @GeneratedValue
	    private int id;
	    private String bookname;
	    private String author;
	    private double price;
	    
	    public Book(int id,String bookname, String author, double price) {
	    	///this.id = id;
	    	this.bookname = bookname;
	    	this.author = author;
	    	this.price = price;
	    }
	    public Book(){
	    	
	    }

		public Integer getId() {
		
			return id;
		}
		public void setId(int id) {
		
			this.id= id ;
		}
		public String getBookName() {
			return bookname;
		}

		public void setBookName(String bookname) {
			this.bookname = bookname;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}


}
