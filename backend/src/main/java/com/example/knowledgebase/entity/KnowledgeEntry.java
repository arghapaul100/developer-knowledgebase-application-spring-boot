package com.example.knowledgebase.entity;

import com.example.knowledgebase.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "knowledge_entries", indexes = {
        @Index(name = "idx_knowledge_title", columnList = "title"),
        @Index(name = "idx_knowledge_category", columnList = "category"),
        @Index(name = "idx_knowledge_tags", columnList = "tags")
})
public class KnowledgeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String problemDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String solutionDescription;

    @Column(columnDefinition = "TEXT")
    private String codeSnippet;

    @Column(length = 600)
    private String tags;

    private String exceptionSignature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;
}
