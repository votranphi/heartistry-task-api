package com.example.heartistry_task_api.Words;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordsService {
    @Autowired
    private WordsRepository wordsRepository;

    public Word save(Word word) {
        return wordsRepository.save(word);
    }

    public List<Word> findWordSetsByWordSetId(Integer id) {
        return wordsRepository.findWordSetsByWordSetId(id);
    }

    public List<Word> findAllWordSets() {
        return wordsRepository.findAll();
    }
}
