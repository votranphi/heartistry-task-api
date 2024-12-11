package com.example.heartistry_task_api.Words;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface WordsRepository extends JpaRepository<Word, Integer> {
    Page<Word> findByIdWordSet(@Param("idWordSet") Integer idWordSet, Pageable pageable);

    @Query("SELECT COUNT(w) FROM Word w WHERE w.idWordSet = :idWordSet")
    Integer countByIdWordSet(@Param("idWordSet") Integer idWordSet);
}
