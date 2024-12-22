package com.example.heartistry_task_api.AuditLogs;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.AuditLogs.Dto.AddDto;
import com.example.heartistry_task_api.Responses.ObjectWithPagination;
import com.example.heartistry_task_api.Responses.Pair;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Tag(name = "AuditLogs", description = "Operations related to Audit Logs management")
@RestController
@RequestMapping(value = "/audit-logs")
public class AuditLogsController {
    @Autowired
    private AuditLogsService auditLogsService = new AuditLogsService();



    @Operation(summary = "Post LEARN word to Audit Logs")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = AuditLog.class)
        ))
    })
    @PostMapping("/add")
    public @ResponseBody ResponseEntity<AuditLog> createLearnedWordToAuditLogs(@RequestBody AddDto wordDto) {
        return ResponseEntity.ok(
            auditLogsService.createAuditLog(
                wordDto.getAction(),
                wordDto.getEntity(),
                wordDto.getEntityId(),
                wordDto.getUserId(),
                wordDto.getUsername(),
                wordDto.getRole(),
                wordDto.getDetails()
            )
        );
    }



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



    @Operation(summary = "Get all Audit Logs with pagination (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = AuditLog.class))
        ))
    })
    @GetMapping("/all/pagination")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getAllAuditLogsPagination(
        @RequestParam Integer page,
        @RequestParam Integer pageSize
    ) {
        ObjectWithPagination response = new ObjectWithPagination(
            auditLogsService.findAllAuditLogsPagination(page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, auditLogsService.countAllAuditLogs())
        );
        return ResponseEntity.ok(response);
    }



    @Operation(summary = "Get words learning statistics per day")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Pair.class))
        ))
    })
    @GetMapping("/statistics")
    public @ResponseBody ResponseEntity<List<Pair>> getWordsLearningStatistics(@RequestAttribute Integer idUser) {
        List<AuditLog> auditLogs = auditLogsService.findByActionAndUserId("LEARN", idUser);

        // Grouping by date and counting the words learned
        Map<String, Integer> statistics = new HashMap<>();
        for (AuditLog auditLog : auditLogs) {
            // Convert Instant to LocalDate
            Instant timestamp = auditLog.getTimestamp();
            LocalDate date = timestamp.atZone(ZoneId.systemDefault()).toLocalDate();
            String dateKey = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

            // Increment the count for the date
            statistics.put(dateKey, statistics.getOrDefault(dateKey, 0) + 1);
        }

        // Convert the map to a list of Pair objects
        List<Pair> response = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            response.add(new Pair(entry.getKey(), String.valueOf(entry.getValue())));
        }

        return ResponseEntity.ok(response);
    }
}