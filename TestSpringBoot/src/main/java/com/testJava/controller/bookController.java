package com.testJava.controller;

import com.testJava.dto.CommonResponse;
import com.testJava.dto.PageResponseDTO;
import com.testJava.pojo.Book;
import com.testJava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class bookController {
    @Autowired
    private BookService bookService;

    // 控制单元
    @RequestMapping(path="/getAllBooks", method= RequestMethod.GET)
    @ResponseBody()
    public String getbooks() {
        List<Book> bks = bookService.getAllBooks();
        String s = "";
        for(Book book: bks) {
            s += book.getName();
            s += book.getAuthor();
        }
        return s;
    }
    @GetMapping("/getBooks")
    public ResponseEntity<CommonResponse<PageResponseDTO<Book>>> getBooks(@RequestParam int currentPage, @RequestParam int pageSize) {
        int totalCount = bookService.getTotalCount();
        List<Book> books = bookService.getBooksByPage(currentPage, pageSize);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize); // 计算总页数
        PageResponseDTO<Book> pageResponse = new PageResponseDTO(books, currentPage, pageSize, totalCount, totalPages);
        CommonResponse<PageResponseDTO<Book>> commonResponse = CommonResponse.success(pageResponse, "查询成功");
        return ResponseEntity.ok(commonResponse);
    }
// 控制单元新增：
    // 0.查：支持get请求，分页查询





    // 1.增：支持post请请求，发送formdata,以支持文件上传
    // 2.删：支持post\delete请求
    // 3.改：支持post\put请求
    // 4. 每个请求都能支持跨域，要新增跨域的配置
    // 5. 支持大文件上传，并支持断点续传
}
