package com.example.heartistry_task_api.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "An list of objects response with it pagination info")
public class ObjectWithPagination {
    @Schema(description = "List of objects", example = "[]")
    private Object response;
    @Schema(description = "Pagination info", example = "{ \"page\": 1, \"pageSize\": 4, \"total\": 13 }")
    public PaginationObject pagination;

    public ObjectWithPagination() {}

    public ObjectWithPagination(Object response, PaginationObject pagination) {
        this.response = response;
        this.pagination = pagination;
    }

    public PaginationObject getPagination() {
        return pagination;
    }
    public Object getResponse() {
        return response;
    }

    public void setPagination(PaginationObject pagination) {
        this.pagination = pagination;
    }
    public void setResponse(Object response) {
        this.response = response;
    }

    public static class PaginationObject {
        private Integer page;
        private Integer pageSize;
        private Integer total;

        public PaginationObject() {}

        public PaginationObject(Integer page, Integer pageSize, Integer total) {
            this.page = page;
            this.pageSize = pageSize;
            this.total = total;
        }

        public Integer getPage() {
            return page;
        }
        public Integer getPageSize() {
            return pageSize;
        }
        public Integer getTotal() {
            return total;
        }
        public void setPage(Integer page) {
            this.page = page;
        }
        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }
        public void setTotal(Integer total) {
            this.total = total;
        }
    }
}
