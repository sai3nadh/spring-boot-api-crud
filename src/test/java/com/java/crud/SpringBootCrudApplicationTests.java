package com.java.crud;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.crud.controller.BookController;
import com.java.crud.entity.Book;
import com.java.crud.service.BookService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.Console;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


class SpringBootCrudApplicationTests {

	
	@Test 
	void contextLoads() { 
		
	}


	private MockMvc mockMvc;

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders

				.standaloneSetup(bookController)
				.build();
		objectMapper = new ObjectMapper();
	}


	@Test public void testAddBook() throws Exception { Book book = new Book();

	book.setBookName("Book test"); book.setAuthor("Author test");
	book.setPrice(249);

	when(bookService.saveBook(any(Book.class))).thenReturn(book);


	mockMvc.perform(post("/addBook") .contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(book))) .andExpect(status().isOk())
	.andExpect(jsonPath("$.bookName").value("Book test"))
	.andExpect(jsonPath("$.price").value(249.0))
	.andExpect(jsonPath("$.author").value("Author test"));

	verify(bookService, times(1)).saveBook(any(Book.class)); 
	}


	@Test
	public void testGetBookById() throws Exception {
		Book book = new Book();
		book.setId(1);
		book.setBookName("idiots");
		book.setAuthor("chethan");

		when(bookService.getBookById(anyInt())).thenReturn(book);

		mockMvc.perform(get("/book/1"))
		.andDo(print()) 
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.bookName").value("idiots"))
		.andExpect(jsonPath("$.author").value("chethan"));

		verify(bookService, times(1)).getBookById(anyInt());
	}

}
