package com.example.heartistry_task_api.Words.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AddDto for adding a Word.")
public class AddDto {
    @Schema(description = "Word Set's id contains this Word", example = "1")
    private Integer idWordSet;
    @Schema(description = "Vocabulary/Word", example = "Derivative")
    private String word;
    @Schema(description = "Note for the Word", example = "Dao ham")
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
