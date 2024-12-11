package com.example.heartistry_task_api.Requests;

public class Pagination {
    private Integer page;
    private Integer pageSize;

    public Pagination() {}

    public Pagination(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
