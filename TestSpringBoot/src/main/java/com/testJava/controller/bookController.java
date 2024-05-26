package com.testJava.controller;

import com.qcloud.cos.model.CompleteMultipartUploadResult;
import com.qcloud.cos.model.PutObjectResult;
import com.testJava.dto.AddBookDTO;
import com.testJava.dto.CommonResponse;
import com.testJava.dto.PageResponseDTO;
import com.testJava.dto.UpdateBookDTO;
import com.testJava.pojo.Book;
import com.testJava.service.BookService;
import com.testJava.service.CosMutipartUploadService;
import com.testJava.service.CosUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.io.File;

@Controller
@RequestMapping("/books")
public class bookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CosUploadService cosUploadService;
    @Autowired
    private CosMutipartUploadService cosMutipartUploadService;

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
                // 将MultipartFile转换为File对象
                Path tempFile = Files.createTempFile("uploaded-", ".tmp");
                Files.write(tempFile, bookFile.getBytes());
                File localFile = tempFile.toFile();
                // 判断文件size
                Double fileSize = (double)(localFile.length() / (1024*1024)); // 获取文件大小，单位是字节
                if(fileSize > 20) {
                    CompleteMultipartUploadResult result = cosMutipartUploadService.MultipartUpload("test-maven-1254467616", "book_contents/" + bookFile.getOriginalFilename(), localFile);
                } else {
                    // 上传文件到COS
                    PutObjectResult result = cosUploadService.uploadFile("test-maven-1254467616", "book_contents/" + bookFile.getOriginalFilename(), localFile);
                }
                // 处理上传结果，例如返回文件的URL等
                String fileUrl = "https://" + "test-maven-1254467616" + ".cos." + "ap-shanghai" + ".myqcloud.com/" + "book_contents/" + bookFile.getOriginalFilename();

                // 清理临时文件
                localFile.delete();
                // 生成Book实例
                Book book = new Book(updateBookDTO.getId(), updateBookDTO.getName(), updateBookDTO.getAuthor(), updateBookDTO.getPrice(), fileUrl, "");
                try {
                    int updateRes = bookService.updateBook(book);
                    if(updateRes > 0) {
                        CommonResponse<String> res = CommonResponse.success("", "更新成功");
                        return ResponseEntity.status(HttpStatus.OK).body(res);
                    } else {
                        CommonResponse<String>  res = CommonResponse.fail(0, "更新失败");
                        return ResponseEntity.status(HttpStatus.OK).body(res);
                    }
                } catch(Exception e) {
                    CommonResponse<String> res = CommonResponse.fail(0, "服务器内部错误");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
                }

            } catch (IOException e) {
                // TODO 日志输出
                CommonResponse<String> res = CommonResponse.fail(0, "文件上传失败："+e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        } else {
            CommonResponse<String> res = CommonResponse.fail(0, "bookFile文件为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

    }
}
