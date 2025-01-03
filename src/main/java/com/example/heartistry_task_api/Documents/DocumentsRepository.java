package com.example.heartistry_task_api.Documents;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Integer>  {
    @Query("SELECT d FROM Document d WHERE d.idUser = :idUser AND d.isApproved = true")
    Page<Document> findByIdUser(@Param("idUser") Integer idUser, Pageable pageable);

    @Query("SELECT d FROM Document d WHERE d.idUser = :idUser AND d.isApproved = true")
    List<Document> findByIdUser(@Param("idUser") Integer idUser);

    @Query("SELECT COUNT(d) FROM Document d WHERE d.idUser = :idUser")
    Integer countUserWordSet(@Param("idUser") Integer idUser);

    @SuppressWarnings("null")
    Page<Document> findAll(Pageable pageable);

    @Query("SELECT COUNT(d) FROM Document d")
    Integer countAllDocuments();
}
