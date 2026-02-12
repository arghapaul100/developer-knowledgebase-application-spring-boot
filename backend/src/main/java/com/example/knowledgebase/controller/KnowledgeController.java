package com.example.knowledgebase.controller;

import com.example.knowledgebase.dto.KnowledgeDtos;
import com.example.knowledgebase.enums.Category;
import com.example.knowledgebase.service.KnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/knowledge")
@RequiredArgsConstructor
@Tag(name = "Knowledge")
public class KnowledgeController {
    private final KnowledgeService knowledgeService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Create a knowledge entry")
    public ResponseEntity<KnowledgeDtos.KnowledgeResponse> create(@Valid @RequestBody KnowledgeDtos.KnowledgeRequest request,
                                                                  Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(knowledgeService.create(request, authentication.getName()));
    }

    @GetMapping
    @Operation(summary = "Get all knowledge entries")
    public Page<KnowledgeDtos.KnowledgeResponse> getAll(@PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return knowledgeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get knowledge entry by ID")
    public KnowledgeDtos.KnowledgeResponse getById(@PathVariable UUID id) {
        return knowledgeService.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Update a knowledge entry")
    public KnowledgeDtos.KnowledgeResponse update(@PathVariable UUID id,
                                                  @Valid @RequestBody KnowledgeDtos.KnowledgeRequest request,
                                                  Authentication authentication) {
        return knowledgeService.update(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Delete a knowledge entry")
    public ResponseEntity<Void> delete(@PathVariable UUID id, Authentication authentication) {
        knowledgeService.delete(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search entries by query/category/tag")
    public Page<KnowledgeDtos.KnowledgeResponse> search(@RequestParam(required = false) String query,
                                                        @RequestParam(required = false) Category category,
                                                        @RequestParam(required = false) String tag,
                                                        @PageableDefault(size = 10, sort = "updatedAt") Pageable pageable) {
        return knowledgeService.search(query, category, tag, pageable);
    }
}
