package com.example.heartistry_task_api.Documents;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.heartistry_task_api.Documents.Dto.AdminUpdateDto;
import com.example.heartistry_task_api.Documents.Dto.UpdateDto;

@Service
public class DocumentsService {
    @Autowired
    private DocumentsRepository documentsRepository;

    public Document save(Document document) {
        return documentsRepository.save(document);
    }

    public List<Document> findAll() {
        return documentsRepository.findAll();
    }

    public Optional<Document> findById(Integer id) {
        return documentsRepository.findById(id);
    }

    @Transactional
    public Optional<Document> updateById(Integer id, UpdateDto updateDto) {
        return documentsRepository.findById(id).map(target -> {
            target.setName(updateDto.getName());
            target.setDescription(updateDto.getDescription());
            return target;
        });
    }

    @Transactional
    public Optional<Document> updateByIdForAdmin(Integer id, AdminUpdateDto adminUpdateDto) {
        return documentsRepository.findById(id).map(target -> {
            target.setName(adminUpdateDto.getName());
            target.setDescription(adminUpdateDto.getDescription());
            target.setIsApproved(adminUpdateDto.getIsApproved());
            return target;
        });
    }

    public void deleteDocumentById(Integer id) {
        documentsRepository.deleteById(id);
    }

    public Page<Document> getSequenceOfDocument(Integer idUser, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return documentsRepository.findByIdUser(idUser, pageable);
    }

    public Integer countUserDocument(Integer idUser) {
        return documentsRepository.countUserWordSet(idUser);
    }

    public List<Document> findAllByIdUser(Integer idUser) {
        return documentsRepository.findByIdUser(idUser);
    }

    public Page<Document> findAllPagination(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return documentsRepository.findAll(pageable);
    }

    public Integer countAllDocuments() {
        return documentsRepository.countAllDocuments();
    }
}
