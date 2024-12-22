package com.example.heartistry_task_api.AuditLogs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogsRepository extends JpaRepository<AuditLog, Integer> {

    @SuppressWarnings("null")
    Page<AuditLog> findAll(Pageable pageable);

    @Query("SELECT COUNT(a) FROM AuditLog a")
    Integer countAllAuditLogs();
    
}