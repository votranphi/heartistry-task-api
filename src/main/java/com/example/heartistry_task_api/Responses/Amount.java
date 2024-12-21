package com.example.heartistry_task_api.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A simple response with object's amount")
public class Amount {
    @Schema(description = "Number of object user want to get", example = "2034")
    private Integer amount;

    public Amount() {}

    public Amount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
