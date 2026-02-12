package com.example.knowledgebase.service.impl;

import com.example.knowledgebase.dto.KnowledgeDtos;
import com.example.knowledgebase.entity.KnowledgeEntry;
import com.example.knowledgebase.entity.User;
import com.example.knowledgebase.enums.Category;
import com.example.knowledgebase.exception.NotFoundException;
import com.example.knowledgebase.mapper.KnowledgeMapper;
import com.example.knowledgebase.repository.KnowledgeEntryRepository;
import com.example.knowledgebase.repository.UserRepository;
import com.example.knowledgebase.service.KnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KnowledgeServiceImpl implements KnowledgeService {
    private final KnowledgeEntryRepository knowledgeRepository;
    private final UserRepository userRepository;
    private final KnowledgeMapper mapper;

    @Override
    public KnowledgeDtos.KnowledgeResponse create(KnowledgeDtos.KnowledgeRequest request, String username) {
        User creator = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        KnowledgeEntry entry = KnowledgeEntry.builder()
                .title(request.title())
                .category(request.category())
                .problemDescription(request.problemDescription())
                .solutionDescription(request.solutionDescription())
                .codeSnippet(request.codeSnippet())
                .tags(request.tags())
                .exceptionSignature(request.exceptionSignature())
                .createdBy(creator)
                .build();

        return mapper.toResponse(knowledgeRepository.save(entry));
    }

    @Override
    public Page<KnowledgeDtos.KnowledgeResponse> getAll(Pageable pageable) {
        return knowledgeRepository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public KnowledgeDtos.KnowledgeResponse getById(UUID id) {
        return mapper.toResponse(knowledgeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Knowledge entry not found")));
    }

    @Override
    public KnowledgeDtos.KnowledgeResponse update(UUID id, KnowledgeDtos.KnowledgeRequest request, String username) {
        KnowledgeEntry entry = knowledgeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Knowledge entry not found"));

        if (!entry.getCreatedBy().getUsername().equals(username)) {
            throw new NotFoundException("Entry not found for current user");
        }

        entry.setTitle(request.title());
        entry.setCategory(request.category());
        entry.setProblemDescription(request.problemDescription());
        entry.setSolutionDescription(request.solutionDescription());
        entry.setCodeSnippet(request.codeSnippet());
        entry.setTags(request.tags());
        entry.setExceptionSignature(request.exceptionSignature());

        return mapper.toResponse(knowledgeRepository.save(entry));
    }

    @Override
    public void delete(UUID id, String username) {
        KnowledgeEntry entry = knowledgeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Knowledge entry not found"));
        if (!entry.getCreatedBy().getUsername().equals(username)) {
            throw new NotFoundException("Entry not found for current user");
        }
        knowledgeRepository.delete(entry);
    }

    @Override
    public Page<KnowledgeDtos.KnowledgeResponse> search(String query, Category category, String tag, Pageable pageable) {
        if (query != null && !query.isBlank()) {
            return knowledgeRepository.fullTextSearch(query, pageable).map(mapper::toResponse);
        }
        return knowledgeRepository.search(query, category, tag, pageable).map(mapper::toResponse);
    }
}
