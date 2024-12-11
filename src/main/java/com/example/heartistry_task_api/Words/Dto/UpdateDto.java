package com.example.heartistry_task_api.Words.Dto;

public class UpdateDto {
    private String word;
    private String note;

    public UpdateDto() {}

    public UpdateDto(String word, String note) {
        this.word = word;
        this.note = note;
    }

    public String getWord() {
        return word;
    }
    public String getNote() {
        return note;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
