package com.site.blog.controller;

import com.site.blog.dto.PostRequestDto;
import com.site.blog.dto.PostResponseDto;
import com.site.blog.service.PostService;
import com.site.global.dto.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blog")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<Page<PostResponseDto>>> getAllPosts(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostResponseDto> posts = postService.getAllPosts(pageable);
        return ResponseEntity.ok(GlobalResponse.success(posts));
    }

    @GetMapping("/{url}")
    public ResponseEntity<GlobalResponse<PostResponseDto>> getPostByUrl(@PathVariable String url) {
        PostResponseDto post = postService.getPostByUrl(url);
        return ResponseEntity.ok(GlobalResponse.success(post));
    }

    @GetMapping("/search")
    public ResponseEntity<GlobalResponse<List<PostResponseDto>>> findByTranslation(@RequestParam String translation) {
        List<PostResponseDto> results = postService.findByTranslation(translation);
        return ResponseEntity.ok(GlobalResponse.success(results));
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<PostResponseDto>> createPost(@Valid @RequestBody PostRequestDto createPost) {
        PostResponseDto newPost = postService.createPost(createPost);
        return new ResponseEntity<>(GlobalResponse.created(newPost), HttpStatus.CREATED);
    }

    @PutMapping("/{url}")
    public ResponseEntity<GlobalResponse<PostResponseDto>> updatePost(@PathVariable String url,
            @Valid @RequestBody PostRequestDto updateDto) {
        PostResponseDto updatedPost = postService.updatePostByUrl(url, updateDto);
        return new ResponseEntity<>(GlobalResponse.success(updatedPost), HttpStatus.OK);
    }

    @DeleteMapping("/{url}")
    public ResponseEntity<GlobalResponse<Void>> deletePost(@PathVariable String url) {
        postService.deletePostByUrl(url);
        return new ResponseEntity<>(GlobalResponse.noContent(), HttpStatus.NO_CONTENT);
    }
}
