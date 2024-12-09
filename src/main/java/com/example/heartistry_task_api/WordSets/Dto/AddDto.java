package com.example.heartistry_task_api.WordSets.Dto;

public class AddDto {
    private String topic;

    public AddDto() {}

    public AddDto(String topic) {
        this.topic = topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }
}
