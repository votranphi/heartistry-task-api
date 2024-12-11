package com.example.heartistry_task_api.Words;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.heartistry_task_api.Requests.Pagination;

@Service
public class WordsService {
    @Autowired
    private WordsRepository wordsRepository;

    public Word save(Word word) {
        return wordsRepository.save(word);
    }

    public Page<Word> findByIdWordSet(Integer idWordSet, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());
        return wordsRepository.findByIdWordSet(idWordSet, pageable);
    }

    public List<Word> findAllWordSets() {
        return wordsRepository.findAll();
    }
}
