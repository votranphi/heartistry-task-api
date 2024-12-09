package com.example.heartistry_task_api.WordSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    public @ResponseBody WordSet addWordSet(@RequestBody WordSet wordSet) {
        return wordSetsService.save(wordSet);
    }

    @GetMapping("/")
    public String getWordSets(
            @RequestAttribute("userId") Integer userId,
            @RequestAttribute("username") String username,
            @RequestAttribute("role") String role) {
        return String.format("User %s with ID %d and role %s is accessing WordSets.", username, userId, role);
    }
}
