package com.testJava.dto;

import javax.validation.constraints.NotNull;

public class AddBookDTO {

    @NotNull(message="书籍名称必填")
    private String name;
    private String author;
    private Integer price;
    private String book_path; // 书籍存放路径
    private String cover_path;

    public @NotNull(message = "书籍名称必填") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "书籍名称必填") String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    public AddBookDTO(String name, String author, Integer price, String book_path, String cover_path) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.book_path = book_path;
        this.cover_path = cover_path;
    }

    public AddBookDTO() {
    }
}
