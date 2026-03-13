package com.site.blog.mapper;

import com.site.blog.dto.CreatePostRequestDto;
import com.site.blog.dto.PostResponseDto;
import com.site.blog.model.Post;

public final class PostMapper {
    private PostMapper() {}

    public static PostResponseDto toResponseDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setUrl(post.getUrl());
        dto.setTranslation(post.getTranslation());
        dto.setPublished(post.isPublished());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }

    public static void updateEntityFromRequestDto(CreatePostRequestDto dto, Post post) {
        post.setUrl(dto.getUrl());
        post.setTranslation(dto.getTranslation());
        post.setPublished(dto.isPublished());
    }
}
