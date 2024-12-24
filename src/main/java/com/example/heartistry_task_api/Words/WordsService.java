package com.example.heartistry_task_api.Words;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.heartistry_task_api.Words.Dto.AdminUpdateDto;
import com.example.heartistry_task_api.Words.Dto.UpdateDto;

@Service
public class WordsService {
    @Autowired
    private WordsRepository wordsRepository;

    public Optional<Word> findById(Integer id) {
        return wordsRepository.findById(id);
    }

    public Word save(Word word) {
        return wordsRepository.save(word);
    }

    public Page<Word> findByIdWordSet(Integer idWordSet, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return wordsRepository.findByIdWordSet(idWordSet, pageable);
    }

    public List<Word> findAllByIdWordSet(Integer idWordSet) {
        return wordsRepository.findByIdWordSet(idWordSet);
    }

    public Integer countByIdWordSet(Integer idWordSet) {
        return wordsRepository.countByIdWordSet(idWordSet);
    }
    
    @Transactional
    public Optional<Word> updateWordById(Integer id, UpdateDto updateDto) {
        return wordsRepository.findById(id).map(target -> {
            target.setNote(updateDto.getNote());
            target.setWord(updateDto.getWord());
            return target; 
        });
    }

    @Transactional
    public Optional<Word> updateWordByIdForAdmin(Integer id, AdminUpdateDto adminUpdateDto) {
        return wordsRepository.findById(id).map(target -> {
            target.setNote(adminUpdateDto.getNote());
            target.setWord(adminUpdateDto.getWord());
            return target; 
        });
    }

    public void deleteWordById(Integer id) {
        wordsRepository.deleteById(id);
    }

    public List<Word> findAllWordSets() {
        return wordsRepository.findAll();
    }

    public Page<Word> findAllWordsWithPagination(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return wordsRepository.findAll(pageable);
    }

    public Integer countAllWords() {
        return wordsRepository.countAllWords();
    }
}
