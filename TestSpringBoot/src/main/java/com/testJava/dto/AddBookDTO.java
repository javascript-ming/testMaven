package com.testJava.dto;

import javax.validation.constraints.NotNull;

public class AddBookDTO {

    @NotNull(message="书籍名称必填")
    private String bookName;

    private String bookAuthor;
    private Integer bookPrice;

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

    public AddBookDTO() {
    }

    public AddBookDTO(String bookName, String bookAuthor, Integer bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }
}
