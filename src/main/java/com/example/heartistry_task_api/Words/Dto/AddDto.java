package com.example.heartistry_task_api.Words.Dto;

public class AddDto {
    private Integer idWordSet;
    private String word;
    private String note;

    public AddDto() {}

    public AddDto(Integer idWordSet, String word, String note) {
        this.idWordSet = idWordSet;
        this.word = word;
        this.note = note;
    }

    public Integer getIdWordSet() {
        return idWordSet;
    }
    public String getWord() {
        return word;
    }
    public String getNote() {
        return note;
    }
    
    public void setIdWordSet(Integer idWordSet) {
        this.idWordSet = idWordSet;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
