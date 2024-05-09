package com.testJava.pojo;

public class Book {
    private int id;
    private String name;
    private String author;
    private double price;
    private String cover_path;
    private String book_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("id的setter");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("name的setter");
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

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    public String getBook_path() {
        return book_path;
    }

    public void setBook_path(String book_path) {
        this.book_path = book_path;
    }

    public Book(int id, String name, String author, double price) {
        System.out.println("book类入参构造器基础版");
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }
    public Book() {
        System.out.println("book类空构造器");
    }
    // 新的构造器，接受所有字段作为参数
    public Book(int id, String name, String author, double price, String cover_path, String book_path) {
        this(id, name, author, price); // 调用原始构造器初始化基础字段
        this.cover_path = cover_path;
        this.book_path = book_path;
        System.out.println("book类入参构造器（完整版）");
    }
}

