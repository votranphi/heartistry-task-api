package com.example.heartistry_task_api.Responses;

public class Pair {
    private String key;
    private String value;

    public Pair() {}

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
