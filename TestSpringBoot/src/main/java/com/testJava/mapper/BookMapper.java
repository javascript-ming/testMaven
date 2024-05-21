package com.testJava.mapper;
import com.testJava.dto.PageResponseDTO;
import com.testJava.dto.CommonResponse;
import com.testJava.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookMapper {
//    List<Book> getAllBooks();
    // 不分页获取所有书籍数组
    List getAllBooks();
    // 分页获取书籍数组
//    List getBooksByPage(int offset, int pageSize);
    List<Book> getBooksByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 获取总个数
    int countTotal();
    // 新增书籍
    int addBook(Book book);
    int updateBook(Book book); // 更新书籍
    int deleteBook(int bookId); // 删除书籍
}