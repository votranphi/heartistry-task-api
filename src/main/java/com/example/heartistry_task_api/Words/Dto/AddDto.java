package com.example.heartistry_task_api.Words.Dto;

public class AddDto {
    private Integer idWordSet;
    private String word;

    public AddDto() {}

    public AddDto(Integer idWordSet, String word) {
        this.idWordSet = idWordSet;
        this.word = word;
    }

    public Integer getIdWordSet() {
        return idWordSet;
    }
    public String getWord() {
        return word;
    }
    
    public void setIdWordSet(Integer idWordSet) {
        this.idWordSet = idWordSet;
    }
    public void setWord(String word) {
        this.word = word;
    }
}
