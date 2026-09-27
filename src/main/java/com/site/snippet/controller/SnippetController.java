package com.site.snippet.controller;

import com.site.global.dto.GlobalResponse;
import com.site.snippet.dto.SnippetRequestDto;
import com.site.snippet.dto.SnippetResponseDto;
import com.site.snippet.service.SnippetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/snippet")
public class SnippetController {
    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<Page<SnippetResponseDto>>> getAllSnippets(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SnippetResponseDto> snippets = snippetService.getAllSnippets(pageable);
        return ResponseEntity.ok(GlobalResponse.success(snippets));
    }

    @GetMapping("/{url}")
    public ResponseEntity<GlobalResponse<SnippetResponseDto>> getSnippetByUrl(@PathVariable String url) {
        SnippetResponseDto snippet = snippetService.getSnippetByUrl(url);
        return ResponseEntity.ok(GlobalResponse.success(snippet));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<GlobalResponse<SnippetResponseDto>> getSnippetByCategory(@PathVariable String category) {
        SnippetResponseDto snippet = snippetService.getSnippetByCategory(category);
        return ResponseEntity.ok(GlobalResponse.success(snippet));
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<SnippetResponseDto>> createSnippet(@Valid @RequestBody SnippetRequestDto createSnippet) {
        SnippetResponseDto newSnippet = snippetService.createSnippet(createSnippet);
        return new ResponseEntity<>(GlobalResponse.created(newSnippet), HttpStatus.CREATED);
    }

    @PutMapping("/{url}")
    public ResponseEntity<GlobalResponse<SnippetResponseDto>> updateSnippet(@PathVariable String url,
            @Valid @RequestBody SnippetRequestDto updateSnippet) {
        SnippetResponseDto updatedSnippet = snippetService.updateSnippetByUrl(url, updateSnippet);
        return new ResponseEntity<>(GlobalResponse.success(updatedSnippet), HttpStatus.OK);
    }

    @DeleteMapping("/url")
    public ResponseEntity<GlobalResponse<Void>> deleteSnippet(@PathVariable String url) {
        snippetService.deleteSnippetByUrl(url);
        return new ResponseEntity<>(GlobalResponse.noContent(), HttpStatus.NO_CONTENT);
    }
}
