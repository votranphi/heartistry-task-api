package com.example.heartistry_task_api.AuditLogs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditLogsService {
    @Autowired
    private AuditLogsRepository auditLogsRepository;

    public AuditLog createAuditLog(String action, String entity, Integer entityId, Integer userId, String username, String role, String details) {
        AuditLog auditLog = new AuditLog(action, entity, entityId, userId, username, role, details);
        return auditLogsRepository.save(auditLog);
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogsRepository.findAll();
    }
}