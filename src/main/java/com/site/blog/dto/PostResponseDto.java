package com.site.blog.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PostResponseDto {
    private String url;
    private String translation;
    private boolean isPublished;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
