package com.example.heartistry_task_api.WordSets;

import org.springframework.beans.factory.annotation.Autowired;

public class WordSetsController {
    @Autowired
    private WordSetsService wordSetsService = new WordSetsService();
}
