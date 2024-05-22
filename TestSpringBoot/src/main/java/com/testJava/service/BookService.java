package com.testJava.service;

import com.testJava.dto.AddBookDTO;
import com.testJava.dto.UpdateBookDTO;
import com.testJava.pojo.Book;
import java.util.List;

public interface BookService {
    List getAllBooks();
    List<Book> getBooksByPage(int currentPage, int pageSize);
    int getTotalCount();
    int addBook(AddBookDTO addBookDTO);
    int updateBook(Book book);
    int deleteBook(int id);
}
