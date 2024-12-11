package com.example.heartistry_task_api.Words;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Word {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer idWordSet;
    private String word;
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
