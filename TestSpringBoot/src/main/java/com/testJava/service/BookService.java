package com.testJava.service;

import com.testJava.pojo.Book;
import java.util.List;

public interface BookService {
    List getAllBooks();
    List<Book> getBooksByPage(int currentPage, int pageSize);
    int getTotalCount();
}
