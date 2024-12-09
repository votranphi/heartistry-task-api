package com.example.heartistry_task_api.Words;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends JpaRepository<Word, Integer> {
    @Query("SELECT w FROM Word w WHERE w.idWordSet = :id")
    List<Word> findWordSetsByWordSetId(@Param("id") Integer id);
}
