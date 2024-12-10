package com.example.heartistry_task_api.WordSets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.WordSets.Dto.AddDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping(value = "/wordsets")
public class WordSetsController {
    @Autowired
    private WordSetsService wordSetsService = new WordSetsService();

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<WordSet> addWordSet(@RequestAttribute("id") Integer id, @RequestBody AddDto addDto) {
        WordSet newWordSet = new WordSet(id, addDto.getTopic());

        return ResponseEntity.ok(wordSetsService.save(newWordSet));
    }

    @GetMapping("/me")
    public @ResponseBody ResponseEntity<List<WordSet>> getMyWordSets(@RequestAttribute("id") Integer id) {
        return ResponseEntity.ok(wordSetsService.findWordSetsByUserId(id));
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<WordSet>> getAllWordSets() {
        return ResponseEntity.ok(wordSetsService.findAllWordSets());
    }
}
