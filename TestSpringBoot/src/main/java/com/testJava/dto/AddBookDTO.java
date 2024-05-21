package com.testJava.dto;

import javax.validation.constraints.NotNull;

public class AddBookDTO {

    @NotNull(message="书籍名称必填")
    private String bookName;

    private String bookAuthor;
    private Integer bookPrice;
    private String bookPath; // 书籍存放路径
    private String bookCover;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public AddBookDTO() {
    }

    public AddBookDTO(String bookName, String bookAuthor, Integer bookPrice, String bookPath, String bookCover) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.bookPath = bookPath;
        this.bookCover = bookCover;
    }

}
