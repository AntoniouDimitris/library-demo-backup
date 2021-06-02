package com.librarydemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarydemo.entity.Book;
import com.librarydemo.entity.Customer;
import com.librarydemo.service.BookService;
import com.librarydemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BookService bookService;
	
	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		
		return customerService.getCustomers();
		
	}
	
	// add mapping for GET /customers/{customerId}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.getCustomer(customerId);
		
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		
		return theCustomer;
	}
	
	// add mapping for POST /customers  - add new customer
	
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
	}
	
	// add mapping for PUT /customers - update existing customer
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	// add mapping for DELETE /customers/{customerId} - delete customer
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		// throw exception if null
		
		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
				
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer id - " + customerId;
	}
	
	// add mapping for GET /books
	@GetMapping("/books")
	public List<Book> getBooks() {
		
		return bookService.getBooks();
	}
	
	// add mapping for POST /books
	@PostMapping("/books")
	public Book addBook(@RequestBody Book theBook) {
		
		// also just in case the pass an id in JSON ... set id to 0
		// this is force a save of new item ... instead of update
		
		bookService.saveBook(theBook);
		
		return theBook;
	}
	
	// add mapping for DELETE /books
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable int bookId) {
		
		Book tempBook = bookService.getBook(bookId);
		
		// throw exception if null
		
		/*if (tempBook == null) {
			throw new CustomerNotFoundException("Customer id not found - " + bookId);
		}*/
				
		bookService.deleteBook(bookId);
		
		return "Deleted book id - " + bookId;
	}
	
	// add mapping for lending a book
	@GetMapping("lendbook/{customerId}/{bookId}")
	public ResponseEntity<Customer> lendBookToCustomer(@PathVariable int customerId, @PathVariable int bookId) {
		
		Customer tempCustomer = customerService.getCustomer(customerId);
		
		Book tempBook = bookService.getBook(bookId);
		
		if (tempCustomer.getBook().getBookId() == 0) {
			
			tempCustomer.setBook(tempBook);
			customerService.saveCustomer(tempCustomer);
			tempBook.setQuantity(tempBook.getQuantity() - 1);
			bookService.saveBook(tempBook);
		}
		
		return new ResponseEntity<>(tempCustomer, HttpStatus.OK);
	}
}


















