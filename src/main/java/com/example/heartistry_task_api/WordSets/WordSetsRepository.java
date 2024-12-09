package com.example.heartistry_task_api.WordSets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSetsRepository extends JpaRepository<WordSet, Integer> {

}
