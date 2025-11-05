package com.site.docs.service;

import com.site.docs.dto.*;

import java.util.List;

public interface PostService {
    /** CREATE POST */
    PostResponseDto createPost(CreatePostRequestDto createDto);

    /** GET LIST POST */
    List<PostResponseDto> getAllPosts();

    /** GET POST BY URL */
    PostResponseDto getPostByUrl(String url);

    /** GET POST BY ID */
    PostResponseDto getPostById(Long id);

    /** UPDATE AN EXISTING POST */
    PostResponseDto updatePost(Long id, CreatePostRequestDto updateDto);

    /** DELETE POST */
    void deletePost(Long id);
}
