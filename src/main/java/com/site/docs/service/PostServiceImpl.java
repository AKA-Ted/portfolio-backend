package com.site.docs.service;

import com.site.docs.dto.CreatePostRequestDto;
import com.site.docs.dto.PostResponseDto;
import com.site.docs.mapper.PostMapper;
import com.site.docs.model.Post;
import com.site.docs.repository.PostRepository;
import com.site.global.exception.ResourceConflictException;
import com.site.global.exception.ResourceNotFoundException;
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
    @Transactional
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostMapper::toResponseDto)
                .collect(Collectors.toList());
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
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return PostMapper.toResponseDto(post);
    }

    @Override
    public PostResponseDto updatePost(Long id, CreatePostRequestDto updateDto) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        PostMapper.updateEntityFromRequestDto(updateDto, existingPost);

        String newUrl = updateDto.getUrl();
        if (!existingPost.getUrl().equals(newUrl)) {
            checkIfUrlExists(newUrl);
        }

        Post updatedPost = postRepository.save(existingPost);
        return PostMapper.toResponseDto(updatedPost);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post", "id", id);
        }
        postRepository.deleteById(id);
    }

    private void checkIfUrlExists(String url) {
        Optional<Post> postByUrl = postRepository.findByUrl(url);

        if (postByUrl.isPresent()) {
            throw new ResourceConflictException("Post", "url", url);
        }

    }
}
