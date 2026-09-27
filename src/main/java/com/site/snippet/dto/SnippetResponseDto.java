package com.site.snippet.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class SnippetResponseDto {
    private String url;
    private String category;
    private String command;
    private String translation;
    private String visualizer;
    private String io;
    private boolean isPublished;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
