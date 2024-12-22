package com.example.heartistry_task_api.Words;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.AuditLogs.AuditLogsService;
import com.example.heartistry_task_api.Responses.Amount;
import com.example.heartistry_task_api.Responses.Detail;
import com.example.heartistry_task_api.Responses.ObjectWithPagination;
import com.example.heartistry_task_api.WordSets.WordSet;
import com.example.heartistry_task_api.WordSets.WordSetsService;
import com.example.heartistry_task_api.Words.Dto.AddDto;
import com.example.heartistry_task_api.Words.Dto.UpdateDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Tag(name = "Words", description = "Operations related to Words management")
@RestController
@RequestMapping(value = "/words")
public class WordsController {
    @Autowired
    private WordsService wordsService = new WordsService();
    @Autowired
    private WordSetsService wordSetsService = new WordSetsService();
    @Autowired
    private AuditLogsService auditLogsService = new AuditLogsService();



    @Operation(summary = "Add a new Word")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully added"),
    })
    @PostMapping("/add")
    public @ResponseBody ResponseEntity<Word> addWord(@RequestAttribute("idUser") Integer idUser, @RequestAttribute("username") String username, @RequestAttribute("role") String role, @RequestBody AddDto addDto) {
        Word word = new Word(addDto.getIdWordSet(), addDto.getWord(), addDto.getNote());

        wordSetsService.updateNoWordsById(word.getIdWordSet(), true);

        Word savedWord = wordsService.save(word);

        // make audit log
        auditLogsService.createAuditLog(
            "CREATE",
            "Word",
            savedWord.getId(),
            idUser,
            username,
            role,
            "Create new word with id " + savedWord.getId() + " in word set with id " + savedWord.getIdWordSet() + " with word " + savedWord.getWord() + " and note " + savedWord.getNote()
        );

        return ResponseEntity.ok(savedWord);
    }



    @Operation(summary = "Get Words by WordSet's id with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got"),
    })
    @GetMapping("/{idWordSet}/pagination")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getWordsByWordSetId(@PathVariable Integer idWordSet, @RequestParam Integer page, @RequestParam Integer pageSize) {
        ObjectWithPagination response = new ObjectWithPagination(
            wordsService.findByIdWordSet(idWordSet, page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, wordsService.countByIdWordSet(idWordSet))
        );

        return ResponseEntity.ok(response);
    }



    @Operation(summary = "Get all Words by WordSet's id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got"),
    })
    @GetMapping("/{idWordSet}/all")
    public @ResponseBody ResponseEntity<List<Word>> getAllWordsByWordSetId(@PathVariable Integer idWordSet) {
        List<Word> words = wordsService.findAllByIdWordSet(idWordSet);

        return ResponseEntity.ok(words);
    }



    @Operation(summary = "Update Word by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Word not found",
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(
            value = "{ \"message\": \"Word Set not found\", \"statusCode\": \"404\" }"
        ))),
        @ApiResponse(responseCode = "403", description = "Update other user's word is for Admin only",
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{ \"message\": \"Update other user's word is for Admin only\", \"statusCode\": \"403\" }"
        ))),
        @ApiResponse(responseCode = "200", description = "Successfully updated",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Word.class)
        ))
    })
    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<?> updateWordById(@RequestAttribute("idUser") Integer idUser, @RequestAttribute("username") String username, @RequestAttribute("role") String role, @PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        Optional<Word> foundWord = wordsService.findById(id);

        if (foundWord.isEmpty()) {
            return new ResponseEntity<Detail>(new Detail("Word not found", 404), HttpStatusCode.valueOf(404));
        }

        if (role.equals("admin")) {
            Word word = wordsService.updateWordById(id, updateDto).get();
            // make audit log
            auditLogsService.createAuditLog(
                "UPDATE",
                "Word",
                word.getId(),
                idUser,
                username,
                role,
                "Update word with id " + word.getId() + " in word set with id " + word.getIdWordSet() + " with word " + word.getWord() + " and note " + word.getNote()
            );
            return ResponseEntity.ok(word);
        }

        Optional<WordSet> linkedWordSet = wordSetsService.findById(foundWord.get().getIdWordSet());
        if (linkedWordSet.get().getIdUser() != idUser) {
            return new ResponseEntity<Detail>(new Detail("Update other user's word is for Admin only", 403), HttpStatusCode.valueOf(403));
        }

        Word word = wordsService.updateWordById(id, updateDto).get();
        // make audit log
        auditLogsService.createAuditLog(
            "UPDATE",
            "Word",
            word.getId(),
            idUser,
            username,
            role,
            "Update word with id " + word.getId() + " in word set with id " + word.getIdWordSet() + " with word " + word.getWord() + " and note " + word.getNote()
        );
        return ResponseEntity.ok(word);
    }



    @Operation(summary = "Delete Word by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "404", description = "Word not found",
        content = @Content(mediaType = "application/json",
        examples = @ExampleObject(
            value = "{ \"message\": \"Word Set not found\", \"statusCode\": \"404\" }"
        ))),
        @ApiResponse(responseCode = "403", description = "Delete other user's word is for Admin only",
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{ \"message\": \"Delete other user's word is for Admin only\", \"statusCode\": \"403\" }"
        ))),
        @ApiResponse(responseCode = "200", description = "Successfully deleted",
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{ \"message\": \"Delete word successfully\", \"statusCode\": \"403\" }"
        ))),
    })
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> deleteById(@RequestAttribute("idUser") Integer idUser, @RequestAttribute("username") String username, @RequestAttribute("role") String role, @PathVariable Integer id) {
        Optional<Word> foundWord = wordsService.findById(id);

        if (foundWord.isEmpty()) {
            return new ResponseEntity<Detail>(new Detail("Word not found", 404), HttpStatusCode.valueOf(404));
        }

        if (role.equals("admin")) {
            // make audit log
            auditLogsService.createAuditLog(
                "DELETE",
                "Word",
                foundWord.get().getId(),
                idUser,
                username,
                role,
                "Delete word with id " + foundWord.get().getId() + " in word set with id " + foundWord.get().getIdWordSet() + " with word " + foundWord.get().getWord() + " and note " + foundWord.get().getNote()
            );
            wordsService.deleteWordById(id);
            wordSetsService.updateNoWordsById(foundWord.get().getIdWordSet(), false);
            return new ResponseEntity<Detail>(new Detail("Delete word successfully", 200), HttpStatusCode.valueOf(200));
        }

        Optional<WordSet> linkedWordSet = wordSetsService.findById(foundWord.get().getIdWordSet());
        if (linkedWordSet.get().getIdUser() != idUser) {
            return new ResponseEntity<Detail>(new Detail("Delete other user's word is for Admin only", 403), HttpStatusCode.valueOf(403));
        }

        // make audit log
        auditLogsService.createAuditLog(
            "DELETE",
            "Word",
            foundWord.get().getId(),
            idUser,
            username,
            role,
            "Delete word with id " + foundWord.get().getId() + " in word set with id " + foundWord.get().getIdWordSet() + " with word " + foundWord.get().getWord() + " and note " + foundWord.get().getNote()
        );
        wordsService.deleteWordById(id);
        wordSetsService.updateNoWordsById(foundWord.get().getIdWordSet(), false);
        return new ResponseEntity<Detail>(new Detail("Delete word successfully", 200), HttpStatusCode.valueOf(200));
    }



    @Operation(summary = "Get number of user's Words")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = Amount.class)
        ))
    })
    @GetMapping("/me/count")
    public @ResponseBody ResponseEntity<Amount> numberOfUsersWords(@RequestAttribute("idUser") Integer idUser) {
        List<WordSet> wordSets = wordSetsService.findAllByIdUser(idUser);

        Integer amount = 0;
        for (WordSet wordSet : wordSets) {
            amount += wordsService.countByIdWordSet(wordSet.getId());
        }
        
        return ResponseEntity.ok(new Amount(amount));
    }
    


    @Operation(summary = "Get all Words (Admin only)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully got",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Word.class))
        ))
    })
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Word>> getAllWords() {
        return ResponseEntity.ok(wordsService.findAllWordSets());
    }
}
