package com.example.heartistry_task_api.Responses;

public class Detail {
    private String message;
    private Integer statusCode;

    public Detail() {}

    public Detail(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
