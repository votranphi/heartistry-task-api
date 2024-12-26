package com.example.heartistry_task_api.Documents.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "AddDto for adding a Document.")
public class AddDto {
    @Schema(description = "Name of the Document", example = "Toan12")
    private String name;
    @Schema(description = "Description of the Document", example = "Toan 12 Chuong Trinh Moi")
    private String description;
    @Schema(description = "Url of the Document", example = "https://res.cloudinary.com/diy6oyo6l/image/upload/v1733592844/image_hdhb1e.png")
    private String url;
    @Schema(description = "Type of the document", example = "pdf")
    private String type;

    public AddDto() {}

    public AddDto(String name, String description, String url, String type) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public String getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setType(String type) {
        this.type = type;
    }
}
