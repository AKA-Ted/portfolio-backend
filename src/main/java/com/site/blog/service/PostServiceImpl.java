package com.site.blog.service;

import com.site.blog.dto.CreatePostRequestDto;
import com.site.blog.dto.PostResponseDto;
import com.site.blog.mapper.PostMapper;
import com.site.blog.model.Post;
import com.site.blog.repository.PostRepository;
import com.site.global.exception.ResourceConflictException;
import com.site.global.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public PostResponseDto createPost(CreatePostRequestDto createDto) {
        checkIfUrlExists(createDto.getUrl());

        Post post = new Post();
        PostMapper.updateEntityFromRequestDto(createDto, post);

        Post savedPost = postRepository.save(post);
        return PostMapper.toResponseDto(savedPost);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(PostMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPostByUrl(String url) {
        Post post = postRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "url", url));
        return PostMapper.toResponseDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> findByTranslation(String translation) {
        return postRepository.findByTranslationContainingIgnoreCase(translation)
                .stream()
                .map(PostMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostResponseDto updatePostByUrl(String url, CreatePostRequestDto updateDto) {
        Post existingPost = postRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "url", url));

        // Check if the new URL is different from the current one and if it already exists
        String newUrl = updateDto.getUrl();
        if (!existingPost.getUrl().equals(newUrl)) {
            checkIfUrlExists(newUrl);
        }

        PostMapper.updateEntityFromRequestDto(updateDto, existingPost);

        Post updatedPost = postRepository.save(existingPost);
        return PostMapper.toResponseDto(updatedPost);
    }

    @Override
    @Transactional
    public void deletePostByUrl(String url) {
        Post post = postRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "url", url));
        postRepository.delete(post);
    }

    private void checkIfUrlExists(String url) {
        Optional<Post> postByUrl = postRepository.findByUrl(url);

        if (postByUrl.isPresent()) {
            throw new ResourceConflictException("Post", "url", url);
        }

    }
}
