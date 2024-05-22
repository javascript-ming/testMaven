package com.testJava.dto;

import javax.validation.constraints.NotNull;

public class UpdateBookDTO{
    @NotNull(message = "id必填")
    private int id;
    private String name;
    private String author;
    private double price;
    public UpdateBookDTO() {
    }

    public UpdateBookDTO(int id, String bookName, String bookAuthor, Integer bookPrice) {
        this.id = id;
        this.name = bookName;
        this.author = bookAuthor;
        this.price = bookPrice;
    }

    @NotNull(message = "id必填")
    public int getId() {
        return id;
    }

    public void setId(@NotNull(message = "id必填") int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
