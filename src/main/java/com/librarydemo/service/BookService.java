package com.librarydemo.service;

import java.util.List;

import com.librarydemo.entity.Book;

public interface BookService {

	public List<Book> getBooks();

	public void saveBook(Book theBook);

	public Book getBook(int theId);

	public void deleteBook(int theId);
}
