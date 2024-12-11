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
    private Integer noWordSets;

    public WordSet() {}

    public WordSet(Integer idUser, String topic, Integer noWordSets) {
        this.idUser = idUser;
        this.topic = topic;
        this.noWordSets = noWordSets;
    }

    public Integer getId() {
        return id;
    }
    public Integer getIdUser() {
        return idUser;
    }
    public String getTopic() {
        return topic;
    }
    public Integer getNoWordSets() {
        return noWordSets;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public void setNoWordSets(Integer noWordSets) {
        this.noWordSets = noWordSets;
    }
}
