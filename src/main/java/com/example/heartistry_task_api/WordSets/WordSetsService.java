package com.example.heartistry_task_api.WordSets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordSetsService {
    @Autowired
    private WordSetsRepository wordSetsRepository;

    public WordSet save(WordSet wordSet) {
        return wordSetsRepository.save(wordSet);
    }

    public List<WordSet> findWordSetsByUserId(Integer id) {
        return wordSetsRepository.findWordSetsByUserId(id);
    }

    public List<WordSet> findAllWordSets() {
        return wordSetsRepository.findAll();
    }
}
