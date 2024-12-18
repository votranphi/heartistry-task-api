package com.example.heartistry_task_api.WordSets;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.heartistry_task_api.Responses.Detail;
import com.example.heartistry_task_api.Responses.ObjectWithPagination;
import com.example.heartistry_task_api.WordSets.Dto.AddDto;
import com.example.heartistry_task_api.WordSets.Dto.UpdateDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    @GetMapping("/me/pagination")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getMyWordSets(@RequestAttribute("idUser") Integer idUser, @RequestParam Integer page, @RequestParam Integer pageSize) {
        ObjectWithPagination response = new ObjectWithPagination(
            wordSetsService.getSequenceOfPost(idUser, page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, wordSetsService.countUserWordSet(idUser))
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me/all")
    public @ResponseBody ResponseEntity<List<WordSet>> getAllWordSets(@RequestAttribute("idUser") Integer idUser) {
        List<WordSet> wordSets = wordSetsService.findAllByIdUser(idUser);

        return ResponseEntity.ok(wordSets);
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<?> updateById(@RequestAttribute("idUser") Integer idUser, @RequestAttribute("role") String role, @PathVariable Integer id, @RequestBody UpdateDto updateDto) {
        Optional<WordSet> foundWordSet = wordSetsService.findById(id);

        if (foundWordSet.isEmpty()) {
            return new ResponseEntity<Detail>(new Detail("Word Set not found", 404), HttpStatusCode.valueOf(404));
        }

        if (role.equals("admin")) {
            WordSet wordSet = wordSetsService.updateById(id, updateDto).get();
            return ResponseEntity.ok(wordSet);
        }

        if (foundWordSet.get().getIdUser() != idUser) {
            return new ResponseEntity<Detail>(new Detail("Update other user's wordset is for Admin only", 403), HttpStatusCode.valueOf(403));
        }

        WordSet wordSet = wordSetsService.updateById(id, updateDto).get();
        return ResponseEntity.ok(wordSet);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Detail> deleteById(@RequestAttribute("idUser") Integer idUser, @RequestAttribute("role") String role, @PathVariable Integer id) {
        Optional<WordSet> foundWordSet = wordSetsService.findById(id);

        if (foundWordSet.isEmpty()) {
            return new ResponseEntity<Detail>(new Detail("Word Set not found", 404), HttpStatusCode.valueOf(404));
        }

        if (role.equals("admin")) {
            wordSetsService.deleteWordById(id);
            return new ResponseEntity<Detail>(new Detail("Delete wordset successfully", 200), HttpStatusCode.valueOf(200));
        }

        if (foundWordSet.get().getIdUser() != idUser) {
            return new ResponseEntity<Detail>(new Detail("Delete other user's wordset is for Admin only", 403), HttpStatusCode.valueOf(403));
        }

        wordSetsService.deleteWordById(id);
        return new ResponseEntity<Detail>(new Detail("Delete wordset successfully", 200), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/recommended/pagination")
    public @ResponseBody ResponseEntity<ObjectWithPagination> getRecommendedWordSetsPagination(@RequestParam Integer page, @RequestParam Integer pageSize) {
        ObjectWithPagination response = new ObjectWithPagination(
            wordSetsService.findRecommendedWordSetsPagination(page, pageSize).toList(),
            new ObjectWithPagination.PaginationObject(page, pageSize, wordSetsService.countRecommendedWordSet())
        );
        return ResponseEntity.ok(response);
    }
    

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<WordSet>> getAllWordSets() {
        return ResponseEntity.ok(wordSetsService.findAllWordSets());
    }
}
