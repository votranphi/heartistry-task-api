package com.example.heartistry_task_api.Words.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UpdateDto for updating a Word.")
public class UpdateDto {
    @Schema(description = "Vocabulary/Word", example = "Derivative")
    private String word;
    @Schema(description = "Note for the Word", example = "Dao ham")
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
