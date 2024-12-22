package com.example.heartistry_task_api.AuditLogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Tag(name = "AuditLogs", description = "Operations related to Audit Logs management")
@RestController
@RequestMapping(value = "/audit-logs")
public class AuditLogsController {
    @Autowired
    private AuditLogsService auditLogsService = new AuditLogsService();

    @Operation(summary = "Get all Audit Logs (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = AuditLog.class))
        ))
    })
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        return ResponseEntity.ok(auditLogsService.getAllAuditLogs());
    }
}
