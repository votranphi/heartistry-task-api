package com.example.heartistry_task_api.WordSets.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UpdateDto for updating a Word Set.")
public class UpdateDto {
    @Schema(description = "Topic of the Word Set", example = "Mathematics")
    private String topic;

    public UpdateDto() {}

    public UpdateDto(String topic) {
        this.topic = topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
