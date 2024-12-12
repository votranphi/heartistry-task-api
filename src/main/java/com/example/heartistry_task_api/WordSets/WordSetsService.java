package com.example.heartistry_task_api.WordSets;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.heartistry_task_api.WordSets.Dto.UpdateDto;

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

    public List<WordSet> findAllById(Integer idUser) {
        return wordSetsRepository.findByIdUser(idUser);
    }

    @Transactional
    public Optional<WordSet> updateById(Integer id, UpdateDto updateDto) {
        return wordSetsRepository.findById(id).map(target -> {
            target.setTopic(updateDto.getTopic());
            return target;
        });
    }

    @Transactional
    public Optional<WordSet> updateNoWordsById(Integer id, boolean isIncreased) {
        return wordSetsRepository.findById(id).map(target -> {
            target.setNoWords(isIncreased ? target.getNoWords() + 1 : target.getNoWords() - 1);
            return target;
        });
    }

    public void deleteWordById(Integer id) {
        wordSetsRepository.deleteById(id);
    }

    public Integer countUserWordSet(Integer idUser) {
        return wordSetsRepository.countUserWordSet(idUser);
    }
}
