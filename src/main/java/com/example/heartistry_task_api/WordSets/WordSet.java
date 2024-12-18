package com.example.heartistry_task_api.WordSets;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Represents a Word Set entity containing words for learning.")
@Entity
public class WordSet {
    @Schema(description = "Unique identifier of the Word Set", example = "1")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Schema(description = "Id of the owner of the Word Set", example = "1")
    private Integer idUser;
    @Schema(description = "Topic of the Word Set", example = "Mathematics")
    private String topic;
    @Schema(description = "Number of words in the Word Set", example = "10")
    private Integer noWords;
    @Schema(description = "Is the Word Set recommended globally", example = "false")
    private Boolean isRecommended;

    public WordSet() {}

    public WordSet(Integer idUser, String topic, Integer noWords) {
        this.idUser = idUser;
        this.topic = topic;
        this.noWords = noWords;
        this.isRecommended = false;
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
    public Boolean getIsRecommended() {
        return isRecommended;
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
    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }
}
