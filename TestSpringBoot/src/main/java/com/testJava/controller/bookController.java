package com.testJava.controller;

import com.testJava.dto.AddBookDTO;
import com.testJava.dto.CommonResponse;
import com.testJava.dto.PageResponseDTO;
import com.testJava.dto.UpdateBookDTO;
import com.testJava.pojo.Book;
import com.testJava.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/books")
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

    // 控制单元新增：
    // 0.查：支持get请求，分页查询
    @GetMapping("/getBooks")
    public ResponseEntity<CommonResponse<PageResponseDTO<Book>>> getBooks(@RequestParam int currentPage, @RequestParam int pageSize) {
        int totalCount = bookService.getTotalCount();
        List<Book> books = bookService.getBooksByPage(currentPage, pageSize);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize); // 计算总页数
        PageResponseDTO<Book> pageResponse = new PageResponseDTO<>(books, currentPage, pageSize, totalCount, totalPages);
        CommonResponse<PageResponseDTO<Book>> commonResponse = CommonResponse.success(pageResponse, "查询成功");
        return ResponseEntity.ok(commonResponse);
    }
    // 1.增：支持post请请求，发送formdata,以支持文件上传
    @PostMapping("/addBook")
    public ResponseEntity<CommonResponse<String>> addBook(@Validated @ModelAttribute AddBookDTO bookDTO, @RequestParam("bookFile") MultipartFile bookFile) {
        // 处理DTO中的字段和上传的文件
        // TODO 获取bookDTO的字段
        // TODO 文件上传只保存文件存储后的本地路径
        CommonResponse<String> res = CommonResponse.success(null, "新增成功");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    // 2.删：支持post\delete请求
    @DeleteMapping("/deleteBook")
    public ResponseEntity<CommonResponse<String>> deleteBook(@RequestParam int id) {
        try{
            int num =  bookService.deleteBook(id);
            if(num >0) {
                CommonResponse<String> res = CommonResponse.success(null, "新增成功");
                return ResponseEntity.status(HttpStatus.OK).body(res);
            } else {
                CommonResponse<String> res = CommonResponse.fail(0, "书籍不存在或已被删除");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
        } catch (Exception e) {
            // TODO 记录日志
            // 返回异常响应
            CommonResponse<String> res = CommonResponse.fail(0, "删除失败：" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

    }
    // 3.改：支持post\put请求
    // 4. 每个请求都能支持跨域，要新增跨域的配置
    // 5. 支持大文件上传，并支持断点续传
    @PostMapping("/updateBook")
    @PutMapping("/updateBook")
    public ResponseEntity<CommonResponse<String>> updateBook(@Validated @ModelAttribute UpdateBookDTO updateBookDTO, @RequestParam("bookFile") MultipartFile bookFile) {
        // 处理DTO中的字段和上传的文件
        // TODO 获取updateBookDTO的字段
        // TODO 文件上传只保存文件存储后的本地路径
        if(!bookFile.isEmpty()) {
            try {
                // TODO 保存文件, 采用腾讯云COS对象存储服务
                String filePath = "/resource/bookFiles/"+ bookFile.getOriginalFilename();
                Files.copy(bookFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                // TODO: 断点续传等
                CommonResponse<String> res = CommonResponse.success(null, "更新成功");
                return ResponseEntity.status(HttpStatus.OK).body(res);
            } catch (IOException e) {
                CommonResponse<String> res = CommonResponse.fail(0, "文件上传失败："+e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        } else {
            CommonResponse<String> res = CommonResponse.fail(0, "bookFile文件为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

    }
}
