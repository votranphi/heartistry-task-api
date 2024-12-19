package com.example.heartistry_task_api.Words;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Represents a Word entity containing vocabulary and its note for learning")
@Entity
public class Word {
    @Schema(description = "Unique identifier of the Word", example = "1")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Schema(description = "Id of the Word Set which it's belong to", example = "1")
    private Integer idWordSet;
    @Schema(description = "Vocabulary/Word", example = "Derivative")
    private String word;
    @Schema(description = "Note for the Word", example = "Dao ham")
    private String note;

    public Word() {}

    public Word(Integer idWordSet, String word, String note) {
        this.idWordSet = idWordSet;
        this.word = word;
        this.note = note;
    }

    public Integer getId() {
        return id;
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
    
    public void setId(Integer id) {
        this.id = id;
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
