package com.example.heartistry_task_api.Words;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.Responses.ObjectWithPagination;
import com.example.heartistry_task_api.Words.Dto.AddDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(value = "/words")
public class WordsController {
    @Autowired
    private WordsService wordsService = new WordsService();

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<Word> addWord(@RequestBody AddDto addDto) {
        Word word = new Word(addDto.getIdWordSet(), addDto.getWord(), addDto.getNote());

        return ResponseEntity.ok(wordsService.save(word));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getAllWordsByWordSetId(@PathVariable Integer id, @RequestParam Integer page, @RequestParam Integer pageSize) {
        ObjectWithPagination response = new ObjectWithPagination(
            wordsService.findByIdWordSet(id, page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, wordsService.countByIdWordSet(id))
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Word>> getAllWords() {
        return ResponseEntity.ok(wordsService.findAllWordSets());
    }
}
