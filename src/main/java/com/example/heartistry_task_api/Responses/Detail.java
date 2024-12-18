package com.example.heartistry_task_api.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "An super normal response structure")
public class Detail {
    @Schema(description = "Message of the response", example = "Unauthorized")
    private String message;
    @Schema(description = "Status code of the response", example = "401")
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
