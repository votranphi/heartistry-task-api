package com.example.heartistry_task_api.Words;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.Responses.Detail;
import com.example.heartistry_task_api.Responses.ObjectWithPagination;
import com.example.heartistry_task_api.Words.Dto.AddDto;
import com.example.heartistry_task_api.Words.Dto.UpdateDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<Word> updateWordById(@PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        Word word = wordsService.updateWordById(id, updateDto).get();

        return ResponseEntity.ok(word);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Detail> deleteById(@PathVariable Integer id) {
        wordsService.deleteWordById(id);

        return new ResponseEntity<Detail>(new Detail("Delete word successfully", 200), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Word>> getAllWords() {
        return ResponseEntity.ok(wordsService.findAllWordSets());
    }
}
