package com.testJava.service.impl;

import com.testJava.dto.AddBookDTO;
import com.testJava.dto.PageResponseDTO;
import com.testJava.dto.UpdateBookDTO;
import com.testJava.mapper.BookMapper;
import com.testJava.pojo.Book;
import com.testJava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    private int totalCount;
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookMapper.getAllBooks();
        return books;
    }
    @Override
    public List<Book> getBooksByPage(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        System.out.println(offset);
        System.out.println(pageSize);
        List<Book> books = bookMapper.getBooksByPage(offset, pageSize);
        return books;
    }
    @Override
    public int getTotalCount() {
        // 这里调用mybatis查询
        // int count = bookRepository.countBooks();
        int count = bookMapper.countTotal(); // 获取书籍总数
        this.totalCount = count;
        return count;
    }

    @Override
    public int addBook(AddBookDTO addBookDTO) {
        // 创建Book实例
        Book book = new Book();
        book.setName(addBookDTO.getName());
        book.setAuthor(addBookDTO.getAuthor());
        book.setPrice(addBookDTO.getPrice());
        book.setBook_path(addBookDTO.getBook_path());
        book.setCover_path(addBookDTO.getCover_path());
        // 调用mybatis的新增
        int res = bookMapper.addBook(book);
        return res;
    }
    @Override
    public int updateBook(Book book) {
        System.out.println(book);
        int res = bookMapper.updateBook(book);
        return res;
    }
    public int deleteBook(int id) {
        int res = bookMapper.deleteBook(id);
        return res;
    }
}
