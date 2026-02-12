package com.example.knowledgebase.service;

import com.example.knowledgebase.dto.KnowledgeDtos;
import com.example.knowledgebase.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface KnowledgeService {
    KnowledgeDtos.KnowledgeResponse create(KnowledgeDtos.KnowledgeRequest request, String username);
    Page<KnowledgeDtos.KnowledgeResponse> getAll(Pageable pageable);
    KnowledgeDtos.KnowledgeResponse getById(UUID id);
    KnowledgeDtos.KnowledgeResponse update(UUID id, KnowledgeDtos.KnowledgeRequest request, String username);
    void delete(UUID id, String username);
    Page<KnowledgeDtos.KnowledgeResponse> search(String query, Category category, String tag, Pageable pageable);
}
