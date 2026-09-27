package com.site.blog.service;

import com.site.blog.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    /** CREATE POST */
    PostResponseDto createPost(PostRequestDto createDto);

    /** GET LIST POST */
    Page<PostResponseDto> getAllPosts(Pageable pageable);

    /** GET POST BY URL */
    PostResponseDto getPostByUrl(String url);

    /** UPDATE AN EXISTING POST */
    PostResponseDto updatePostByUrl(String url, PostRequestDto updateDto);

    /** DELETE POST */
    void deletePostByUrl(String url);

    /** SEARCH POST */
    List<PostResponseDto> findByTranslation(String query);
}
