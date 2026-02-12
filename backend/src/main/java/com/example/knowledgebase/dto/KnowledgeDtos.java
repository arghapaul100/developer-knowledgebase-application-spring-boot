package com.example.knowledgebase.dto;

import com.example.knowledgebase.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public class KnowledgeDtos {
    public record KnowledgeRequest(
            @NotBlank @Size(max = 255) String title,
            @NotNull Category category,
            @NotBlank String problemDescription,
            @NotBlank String solutionDescription,
            String codeSnippet,
            String tags,
            String exceptionSignature
    ) {}

    public record KnowledgeResponse(
            UUID id,
            String title,
            Category category,
            String problemDescription,
            String solutionDescription,
            String codeSnippet,
            String tags,
            String exceptionSignature,
            UUID createdById,
            String createdByUsername,
            Instant createdAt,
            Instant updatedAt
    ) {}
}
