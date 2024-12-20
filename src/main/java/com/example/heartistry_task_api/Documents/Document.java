package com.example.heartistry_task_api.Documents;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Represents a Document entity containing document info and its url.")
@Entity
public class Document {
    @Schema(description = "Unique identifier of the Document", example = "1")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    @Schema(description = "Id of the owner of the Document", example = "1")
    private Integer idUser;
    @Schema(description = "Name of the Document", example = "Toan12")
    private String name;
    @Schema(description = "Description of the Document", example = "Toan 12 Chuong Trinh Moi")
    private String description;
    @Schema(description = "Url of the Document", example = "https://res.cloudinary.com/diy6oyo6l/image/upload/v1733592844/image_hdhb1e.png")
    private String url;

    public Document() {}

    public Document(Integer idUser, String name, String description, String url) {
        this.idUser = idUser;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }
    public Integer getId() {
        return id;
    }
    public Integer getIdUser() {
        return idUser;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
