package com.testJava.dto;

import java.util.List;

//分页返回体
public class PageResponseDTO<T> {
    private List<T> content; // 当前页数据列表
    private int currentPage; // 当前页码
    private int pageSize; // 每页记录数
    private int totalCount; // 总记录数
    private int totalPage; // 总页数

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    // 可能还需要其他方法，

    public int getTotalPages() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }

    public PageResponseDTO(List<T> content, int currentPage, int pageSize, int totalCount, int totalPage) {
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    public PageResponseDTO() {
    }
}
