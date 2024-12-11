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

    public Word() {}

    public Word(Integer idWordSet, String word) {
        this.idWordSet = idWordSet;
        this.word = word;
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
    
    public void setId(Integer id) {
        this.id = id;
    }
    public void setIdWordSet(Integer idWordSet) {
        this.idWordSet = idWordSet;
    }
    public void setWord(String word) {
        this.word = word;
    }
}