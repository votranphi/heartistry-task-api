package com.example.heartistry_task_api.WordSets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.Responses.ObjectWithPagination;
import com.example.heartistry_task_api.WordSets.Dto.AddDto;

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
    public @ResponseBody ResponseEntity<WordSet> addWordSet(@RequestAttribute("idUser") Integer idUser, @RequestBody AddDto addDto) {
        WordSet newWordSet = new WordSet(idUser, addDto.getTopic(), 0);

        return ResponseEntity.ok(wordSetsService.save(newWordSet));
    }

    @GetMapping("/me")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getMyWordSets(@RequestAttribute("idUser") Integer idUser, @RequestParam Integer page, @RequestParam Integer pageSize) {
        ObjectWithPagination response = new ObjectWithPagination(
            wordSetsService.getSequenceOfPost(idUser, page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, wordSetsService.countUserWordSet(idUser))
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<WordSet>> getAllWordSets() {
        return ResponseEntity.ok(wordSetsService.findAllWordSets());
    }
}
