package com.site.docs.mapper;

import com.site.docs.dto.CreatePostRequestDto;
import com.site.docs.dto.PostResponseDto;
import com.site.docs.model.Post;

public final class PostMapper {
    private PostMapper() {}

    public static PostResponseDto toResponseDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setTitle(post.getTitle());
        dto.setUrl(post.getUrl());
        dto.setContent(post.getContent());
        dto.setPublished(post.isPublished());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }

    public static void updateEntityFromRequestDto(CreatePostRequestDto dto, Post post) {
        post.setTitle(dto.getTitle());
        post.setUrl(dto.getUrl());
        post.setContent(dto.getContent());
        post.setPublished(dto.isPublished());
    }
}
