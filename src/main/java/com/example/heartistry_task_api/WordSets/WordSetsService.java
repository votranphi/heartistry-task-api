package com.example.heartistry_task_api.WordSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordSetsService {
    @Autowired
    private WordSetsRepository wordSetsRepository;

    public void save(WordSets wordSets) {
        wordSetsRepository.save(wordSets);
    }
}
