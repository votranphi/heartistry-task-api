package com.example.heartistry_task_api.Documents.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UpdateDto for updating a Document (for Admin).")
public class AdminUpdateDto {
    @Schema(description = "Name of the Document", example = "Toan12")
    private String name;
    @Schema(description = "Description of the Document", example = "Toan 12 Chuong Trinh Moi")
    private String description;

    public AdminUpdateDto() {}

    public AdminUpdateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
}
