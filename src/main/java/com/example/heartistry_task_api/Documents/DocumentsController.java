package com.example.heartistry_task_api.Documents;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.AuditLogs.AuditLogsService;
import com.example.heartistry_task_api.Documents.Dto.AddDto;
import com.example.heartistry_task_api.Documents.Dto.UpdateDto;
import com.example.heartistry_task_api.Responses.Detail;
import com.example.heartistry_task_api.Responses.ObjectWithPagination;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;



@Tag(name = "Documents", description = "Operations related to Documents management")
@RestController
@RequestMapping(value = "/documents")
public class DocumentsController {
    @Autowired
    private DocumentsService documentsService = new DocumentsService();
    @Autowired
    private AuditLogsService auditLogsService = new AuditLogsService();



    @Operation(summary = "Add a new Document")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully added"),
    })
    @PostMapping("/add")
    public @ResponseBody ResponseEntity<Document> addDocument(
        @RequestAttribute("idUser") Integer idUser,
        @RequestAttribute("username") String username,
        @RequestAttribute("role") String role,
        @RequestBody AddDto addDto
    ) {
        Document newDocument = new Document(idUser, addDto.getName(), addDto.getDescription(), addDto.getUrl());

        Document savedDocument = documentsService.save(newDocument);

        // make audit log
        auditLogsService.createAuditLog(
            "CREATE",
            "Document",
            savedDocument.getId(),
            idUser,
            username,
            role,
            "Create new document with name: " + savedDocument.getName() + " and description: " + savedDocument.getDescription() + " and url: " + savedDocument.getUrl()
        );

        return ResponseEntity.ok(savedDocument);
    }



    @Operation(summary = "Get Documents with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got"),
    })
    @GetMapping("/me/pagination")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getMyDocuments(
        @RequestAttribute("idUser") Integer idUser,
        @RequestParam Integer page,
        @RequestParam Integer pageSize
    ) {
        ObjectWithPagination response = new ObjectWithPagination(
            documentsService.getSequenceOfDocument(idUser, page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, documentsService.countUserDocument(idUser))
        );

        return ResponseEntity.ok(response);
    }


    
    @Operation(summary = "Get all Documents")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got"),
    })
    @GetMapping("/me/all")
    public @ResponseBody ResponseEntity<List<Document>> getAllDocuments(@RequestAttribute("idUser") Integer idUser) {
        List<Document> documents = documentsService.findAllByIdUser(idUser);

        return ResponseEntity.ok(documents);
    }



    @Operation(summary = "Update Document info by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Document not found",
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(
            value = "{ \"message\": \"Document not found\", \"statusCode\": \"404\" }"
        ))),
        @ApiResponse(responseCode = "403", description = "Update other user's document is for Admin only",
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{ \"message\": \"Update other user's document is for Admin only\", \"statusCode\": \"403\" }"
        ))),
        @ApiResponse(responseCode = "200", description = "Successfully updated",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Document.class)
        ))
    })
    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<?> updateDocument(
        @RequestAttribute("idUser") Integer idUser,
        @RequestAttribute("username") String username,
        @RequestAttribute("role") String role,
        @PathVariable Integer id,
        @RequestBody UpdateDto updateDto
    ) {
        Optional<Document> foundDocument = documentsService.findById(id);

        if (foundDocument.isEmpty()) {
            return new ResponseEntity<Detail>(new Detail("Document not found", 404), HttpStatusCode.valueOf(404));
        }

        if (role.equals("admin")) {
            Document document = documentsService.updateById(id, updateDto).get();
            // make audit log
            auditLogsService.createAuditLog(
                "UPDATE",
                "Document",
                document.getId(),
                idUser,
                username,
                role,
                "Update document with name: " + document.getName() + " and description: " + document.getDescription() + " and url: " + document.getUrl()
            );
            return ResponseEntity.ok(document);
        }

        if (foundDocument.get().getIdUser() != idUser) {
            return new ResponseEntity<Detail>(new Detail("Update other user's document is for Admin only", 403), HttpStatusCode.valueOf(403));
        }

        Document document = documentsService.updateById(id, updateDto).get();
        // make audit log
        auditLogsService.createAuditLog(
            "UPDATE",
            "Document",
            document.getId(),
            idUser,
            username,
            role,
            "Update document with name: " + document.getName() + " and description: " + document.getDescription() + " and url: " + document.getUrl()
        );
        return ResponseEntity.ok(document);
    }



    @Operation(summary = "Delete Document by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Document not found",
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(
            value = "{ \"message\": \"Document not found\", \"statusCode\": \"404\" }"
        ))),
        @ApiResponse(responseCode = "403", description = "Delete other user's document is for Admin only",
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{ \"message\": \"Delete other user's document is for Admin only\", \"statusCode\": \"403\" }"
        ))),
        @ApiResponse(responseCode = "200", description = "Successfully deleted",
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{ \"message\": \"Delete document successfully\", \"statusCode\": \"403\" }"
        ))),
    })
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteDocument(
        @RequestAttribute("idUser") Integer idUser,
        @RequestAttribute("username") String username,
        @RequestAttribute("role") String role,
        @PathVariable Integer id
    ) {
        Optional<Document> foundDocument = documentsService.findById(id);

        if (foundDocument.isEmpty()) {
            return new ResponseEntity<Detail>(new Detail("Document not found", 404), HttpStatusCode.valueOf(404));
        }

        if (role.equals("admin")) {
            // make audit log
            auditLogsService.createAuditLog(
                "DELETE",
                "WordSet",
                foundDocument.get().getId(),
                idUser,
                username,
                role,
                "Delete document with name: " + foundDocument.get().getName() + " and description: " + foundDocument.get().getDescription() + " and url: " + foundDocument.get().getUrl()
            );
            documentsService.deleteDocumentById(id);
            return new ResponseEntity<Detail>(new Detail("Delete document successfully", 200), HttpStatusCode.valueOf(200));
        }

        if (foundDocument.get().getIdUser() != idUser) {
            return new ResponseEntity<Detail>(new Detail("Delete other user's document is for Admin only", 403), HttpStatusCode.valueOf(403));
        }

        // make audit log
        auditLogsService.createAuditLog(
            "DELETE",
            "WordSet",
            foundDocument.get().getId(),
            idUser,
            username,
            role,
            "Delete document with name: " + foundDocument.get().getName() + " and description: " + foundDocument.get().getDescription() + " and url: " + foundDocument.get().getUrl()
        );
        documentsService.deleteDocumentById(id);
        return new ResponseEntity<Detail>(new Detail("Delete document successfully", 200), HttpStatusCode.valueOf(200));
    }



    @Operation(summary = "Get all Documents (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Document.class))
        ))
    })
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentsService.findAll());
    }
    
    @Operation(summary = "Get all Documents with pagination (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ObjectWithPagination.class)
        ))
    })
    @GetMapping("/all/pagination")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getAllDocumentsWithPagination(
        @RequestParam Integer page,
        @RequestParam Integer pageSize
    ) {
        ObjectWithPagination response = new ObjectWithPagination(
            documentsService.findAllPagination(page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, documentsService.countAllDocuments())
        );

        return ResponseEntity.ok(response);
    }
}
