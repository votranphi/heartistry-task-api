package com.example.heartistry_task_api.WordSets;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WordSets {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Integer key1;
    private String key2;
    private String key3;
    private String key4;
    private String key5;
    private String key6;

    public Integer getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }
    public String getKey3() {
        return key3;
    }
    public String getKey4() {
        return key4;
    }
    public String getKey5() {
        return key5;
    }
    public String getKey6() {
        return key6;
    }
    public void setKey1(Integer key1) {
        this.key1 = key1;
    }
    public void setKey2(String key2) {
        this.key2 = key2;
    }
    public void setKey3(String key3) {
        this.key3 = key3;
    }
    public void setKey4(String key4) {
        this.key4 = key4;
    }
    public void setKey5(String key5) {
        this.key5 = key5;
    }
    public void setKey6(String key6) {
        this.key6 = key6;
    }
}
