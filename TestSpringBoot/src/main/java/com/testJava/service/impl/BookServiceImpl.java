package com.testJava.service.impl;

import com.testJava.dto.PageResponseDTO;
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
        // 为了示例，这里我们直接返回一个模拟的总数
        int count = bookMapper.countTotal(); // 获取书籍总数
        this.totalCount = count;
        return count;
    }

}
