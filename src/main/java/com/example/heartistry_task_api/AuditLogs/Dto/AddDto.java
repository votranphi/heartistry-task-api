package com.example.heartistry_task_api.AuditLogs.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AddDto {
    @Schema(description = "Action made", example = "CREATE")
    private String action; // 'CREATE', 'UPDATE', 'DELETE', 'SIGNUP', 'LOGIN',...
    @Schema(description = "Entity affected by the action", example = "User")
    private String entity; // 'User', 'Otp',...
    @Schema(description = "ID of the entity affected", example = "123")
    private Integer entityId; // -1: unknown,...
    @Schema(description = "ID of the user who performed the action", example = "100")
    private Integer userId; // -1: unknown,...
    @Schema(description = "Username of the user who performed the action", example = "nguyenvana")
    private String username;
    @Schema(description = "Role of the user who performed the action", example = "admin")
    private String role; // 'user', 'admin'
    @Schema(description = "Details of the changes", example = "Updated email address")
    private String details;

    public AddDto() {}

    public AddDto(String action, String entity, Integer entityId, Integer userId, String username, String role, String details) {
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.details = details;
    }

    public String getAction() {
        return action;
    }
    public String getDetails() {
        return details;
    }
    public String getEntity() {
        return entity;
    }
    public Integer getEntityId() {
        return entityId;
    }
    public String getRole() {
        return role;
    }
    public Integer getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }

    public void setAction(String action) {
        this.action = action;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public void setEntity(String entity) {
        this.entity = entity;
    }
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
