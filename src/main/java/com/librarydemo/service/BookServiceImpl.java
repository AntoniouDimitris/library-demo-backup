package com.librarydemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarydemo.dao.BookDAO;
import com.librarydemo.entity.Book;

@Service
public class BookServiceImpl implements BookService {

		@Autowired
		private BookDAO bookDAO;
		
		@Override
		@Transactional
		public List<Book> getBooks() {
			return bookDAO.getBooks();
		}

		@Override
		@Transactional
		public void saveBook(Book theBook) {

			bookDAO.saveBook(theBook);
		}

		@Override
		@Transactional
		public Book getBook(int theId) {
			
			return bookDAO.getBook(theId);
		}

		@Override
		@Transactional
		public void deleteBook(int theId) {
			
			bookDAO.deleteBook(theId);
		}

}
