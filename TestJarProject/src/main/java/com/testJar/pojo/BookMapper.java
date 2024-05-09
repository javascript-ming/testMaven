package com.testJar.pojo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    // 查询所有书籍
    List<Book> selectAllBooks();
}
