package com.example.heartistry_task_api.Words;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        Word word = new Word(addDto.getIdWordSet(), addDto.getWord());

        return ResponseEntity.ok(wordsService.save(word));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<List<Word>> getAllWordsByWordSetId(@PathVariable Integer id) {
        return ResponseEntity.ok(wordsService.findWordSetsByWordSetId(id));
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Word>> getAllWords() {
        return ResponseEntity.ok(wordsService.findAllWordSets());
    }
}
