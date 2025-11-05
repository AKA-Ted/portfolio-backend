package com.site.docs.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PostResponseDto {
    private String title;
    private String url;
    private String content;
    private boolean isPublished;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
