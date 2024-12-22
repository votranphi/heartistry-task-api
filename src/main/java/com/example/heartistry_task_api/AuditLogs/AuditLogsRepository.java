package com.example.heartistry_task_api.AuditLogs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AuditLogsRepository extends JpaRepository<AuditLog, Integer> {
    @Query("SELECT a FROM AuditLog a WHERE a.action = :action AND a.userId = :userId")
    List<AuditLog> findByActionAndUserId(String action, Integer userId);

    @SuppressWarnings("null")
    Page<AuditLog> findAll(Pageable pageable);

    @Query("SELECT COUNT(a) FROM AuditLog a")
    Integer countAllAuditLogs();
}