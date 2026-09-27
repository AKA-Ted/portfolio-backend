package com.site.snippet.mapper;

import com.site.blog.dto.PostResponseDto;
import com.site.snippet.dto.SnippetRequestDto;
import com.site.snippet.dto.SnippetResponseDto;
import com.site.snippet.model.Snippet;
import com.site.snippet.model.Visualizer;

public class SnippetMapper {
    private SnippetMapper() {}

    public static SnippetResponseDto toResponseDto(Snippet snippet){
        SnippetResponseDto dto = new SnippetResponseDto();
        dto.setUrl(snippet.getUrl());
        dto.setCategory(snippet.getCategory());
        dto.setCommand(snippet.getCommand());
        dto.setTranslation(snippet.getTranslation());
        dto.setVisualizer(snippet.getVisualizer() != null ? snippet.getVisualizer().name() : null);
        dto.setIo(snippet.getIo());
        dto.setPublished(snippet.isPublished());
        dto.setCreatedAt(snippet.getCreatedAt());
        dto.setUpdatedAt(snippet.getUpdatedAt());
        return dto;
    }

    public static void updateSnippetFromRequestDto(SnippetRequestDto dto, Snippet snippet){
        snippet.setCategory(dto.getCategory());
        snippet.setCommand(dto.getCommand());
        snippet.setUrl(dto.getUrl());
        snippet.setTranslation(dto.getTranslation());
        snippet.setVisualizer(Visualizer.valueOf(dto.getVisualizer()));
        snippet.setIo(dto.getIo());
        snippet.setPublished(dto.isPublished());
    }
}
