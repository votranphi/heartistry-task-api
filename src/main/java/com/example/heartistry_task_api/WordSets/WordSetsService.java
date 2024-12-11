package com.example.heartistry_task_api.WordSets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WordSetsService {
    @Autowired
    private WordSetsRepository wordSetsRepository;

    public WordSet save(WordSet wordSet) {
        return wordSetsRepository.save(wordSet);
    }

    public List<WordSet> findAllWordSets() {
        return wordSetsRepository.findAll();
    }

    // function to ge a sequence of continuous wordsets
    public Page<WordSet> getSequenceOfPost(Integer idUser, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return wordSetsRepository.findByIdUser(idUser, pageable);
    }

    public Integer countUserWordSet(Integer idUser) {
        return wordSetsRepository.countUserWordSet(idUser);
    }
}
