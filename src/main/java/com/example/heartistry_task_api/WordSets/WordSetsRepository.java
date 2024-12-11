package com.example.heartistry_task_api.WordSets;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSetsRepository extends JpaRepository<WordSet, Integer> {
    Page<WordSet> findByIdUser(@Param("idUser") Integer idUser, Pageable pageable);

    @Query("SELECT COUNT(w) FROM WordSet w WHERE w.idUser = :idUser")
    Integer countUserWordSet(@Param("idUser") Integer idUser);
}
