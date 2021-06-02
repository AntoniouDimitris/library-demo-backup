package com.librarydemo.dao;

import java.util.List;

import com.librarydemo.entity.Book;

public interface BookDAO {

	public List<Book> getBooks();
	
	public void saveBook(Book theBook);

	public Book getBook(int theId);

	public void deleteBook(int theId);
}
