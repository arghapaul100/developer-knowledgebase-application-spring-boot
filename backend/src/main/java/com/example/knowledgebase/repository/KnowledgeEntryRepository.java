package com.example.knowledgebase.repository;

import com.example.knowledgebase.entity.KnowledgeEntry;
import com.example.knowledgebase.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface KnowledgeEntryRepository extends JpaRepository<KnowledgeEntry, UUID> {

    @Query("""
            SELECT k FROM KnowledgeEntry k
            WHERE (:category IS NULL OR k.category = :category)
            AND (:tag IS NULL OR lower(k.tags) LIKE lower(concat('%', :tag, '%')))
            AND (
                :query IS NULL OR
                lower(k.title) LIKE lower(concat('%', :query, '%')) OR
                lower(k.problemDescription) LIKE lower(concat('%', :query, '%')) OR
                lower(k.solutionDescription) LIKE lower(concat('%', :query, '%')) OR
                lower(k.tags) LIKE lower(concat('%', :query, '%'))
            )
            """)
    Page<KnowledgeEntry> search(@Param("query") String query,
                                @Param("category") Category category,
                                @Param("tag") String tag,
                                Pageable pageable);

    @Query(value = """
            SELECT * FROM knowledge_entries k
            WHERE to_tsvector('english', coalesce(k.title,'') || ' ' || coalesce(k.problem_description,'') || ' ' || coalesce(k.solution_description,'') || ' ' || coalesce(k.tags,''))
            @@ plainto_tsquery('english', :query)
            """, nativeQuery = true)
    Page<KnowledgeEntry> fullTextSearch(@Param("query") String query, Pageable pageable);

    Optional<KnowledgeEntry> findByIdAndCreatedByUsername(UUID id, String username);
}
