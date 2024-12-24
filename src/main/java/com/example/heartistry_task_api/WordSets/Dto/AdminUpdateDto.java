package com.example.heartistry_task_api.WordSets.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "UpdateDto for updating a Word Set (for Admin).")
public class AdminUpdateDto {
    @Schema(description = "Is the Word Set recommended globally", example = "false")
    private Boolean isRecommended;

    public AdminUpdateDto() {}

    public AdminUpdateDto(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }
}
