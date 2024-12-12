package com.example.heartistry_task_api.WordSets.Dto;

public class UpdateDto {
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
