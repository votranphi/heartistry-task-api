package com.example.heartistry_task_api.Documents.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UpdateDto for updating a Document (for Admin).")
public class AdminUpdateDto {
    @Schema(description = "Name of the Document", example = "Toan12")
    private String name;
    @Schema(description = "Description of the Document", example = "Toan 12 Chuong Trinh Moi")
    private String description;
    @Schema(description = "Is this document approved", example = "false")
    private Boolean isApproved;

    public AdminUpdateDto() {}

    public AdminUpdateDto(String name, String description, Boolean isApproved) {
        this.name = name;
        this.description = description;
        this.isApproved = isApproved;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }
}
