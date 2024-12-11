package com.example.heartistry_task_api.WordSets;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WordSet {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer idUser;
    private String topic;
    private Integer noWords;

    public WordSet() {}

    public WordSet(Integer idUser, String topic, Integer noWords) {
        this.idUser = idUser;
        this.topic = topic;
        this.noWords = noWords;
    }

    public Integer getId() {
        return id;
    }
    public Integer getIdUser() {
        return idUser;
    }
    public Integer getNoWords() {
        return noWords;
    }
    public String getTopic() {
        return topic;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public void setNoWords(Integer noWords) {
        this.noWords = noWords;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
}
