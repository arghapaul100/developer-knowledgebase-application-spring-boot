package com.example.knowledgebase.mapper;

import com.example.knowledgebase.dto.KnowledgeDtos;
import com.example.knowledgebase.entity.KnowledgeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KnowledgeMapper {
    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "createdByUsername", source = "createdBy.username")
    KnowledgeDtos.KnowledgeResponse toResponse(KnowledgeEntry entry);
}
