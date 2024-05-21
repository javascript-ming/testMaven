package com.testJava.dto;

import javax.validation.constraints.NotNull;

public class UpdateBookDTO extends AddBookDTO{
    @NotNull(message = "id必填")
    private int id;
    public UpdateBookDTO() {
    }

    public UpdateBookDTO(int id, String bookName, String bookAuthor, Integer bookPrice, String bookPath, String bookCover) {
        super(bookName, bookAuthor, bookPrice, bookPath, bookCover);
        this.id = id;
    }

    @NotNull(message = "id必填")
    public int getId() {
        return id;
    }

    public void setId(@NotNull(message = "id必填") int id) {
        this.id = id;
    }
}
