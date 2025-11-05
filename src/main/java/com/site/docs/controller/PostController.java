package com.site.docs.controller;

import com.site.docs.dto.CreatePostRequestDto;
import com.site.docs.dto.PostResponseDto;
import com.site.docs.service.PostService;
import com.site.global.dto.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/docs")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(GlobalResponse.success(posts).getData());
    }

    @GetMapping("/{url}")
    public ResponseEntity<PostResponseDto> getPostByUrl(@PathVariable String url) {
        PostResponseDto post = postService.getPostByUrl(url);
        return ResponseEntity.ok(GlobalResponse.success(post).getData());
    }


    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody CreatePostRequestDto createDto) {
        PostResponseDto newPost = postService.createPost(createDto);
        return new ResponseEntity<>(GlobalResponse.success(newPost).getData(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse<PostResponseDto>> updatePost(@PathVariable Long id,
                                                                      @Valid @RequestBody CreatePostRequestDto updateDto) {
        PostResponseDto updatedPost = postService.updatePost(id, updateDto);
        return new ResponseEntity<>(GlobalResponse.success(updatedPost), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(GlobalResponse.noContent(), HttpStatus.NO_CONTENT);
    }
}
