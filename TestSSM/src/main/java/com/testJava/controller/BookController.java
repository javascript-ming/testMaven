package com.testJava.controller;

import com.testJava.pojo.Book;
import com.testJava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/getAllBooks", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String findAllBook() {
        List<Book> bkl = bookService.findAllBook();
        System.out.println("书籍总数："+ bkl.size());
        String books ="";
        for (Book book: bkl) {
            books = books + book.getName();
            books = books + book.getAuthor();
        };
        return books;
    }
}
