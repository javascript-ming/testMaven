package com.testJava.service.impl;

import com.testJava.mapper.BookMapper;
import com.testJava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    public List findAllBook() {
        return bookMapper.selectAll();
    }
}
