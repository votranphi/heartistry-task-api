package com.example.heartistry_task_api.WordSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordSetsService {
    @Autowired
    private WordSetsRepository wordSetsRepository;

    public WordSets save(WordSets wordSets) {
        return wordSetsRepository.save(wordSets);
    }
}
