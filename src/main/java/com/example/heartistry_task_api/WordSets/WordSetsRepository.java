package com.example.heartistry_task_api.WordSets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSetsRepository extends JpaRepository<WordSet, Integer> {
    @Query("SELECT w FROM WordSet w WHERE w.idUser = :id")
    List<WordSet> findWordSetsByUserId(@Param("id") Integer id);
}
