package com.example.heartistry_task_api.WordSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/wordsets")
public class WordSetsController {
    @Autowired
    private WordSetsService wordSetsService = new WordSetsService();

    @PostMapping("/add")
    public @ResponseBody WordSetsDTO postMethodName(@RequestBody WordSets wordSets) {
        WordSetsDTO wordSets2 = new WordSetsDTO(wordSetsService.save(wordSets));
        return wordSets2;
    }
}
