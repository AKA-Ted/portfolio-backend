package com.site.snippet.service;

import com.site.snippet.dto.SnippetRequestDto;
import com.site.snippet.dto.SnippetResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SnippetService {
    /** CREATE SNIPPET */
    SnippetResponseDto createSnippet(SnippetRequestDto createSnippet);

    /** GET LIST SNIPPETS */
    Page<SnippetResponseDto> getAllSnippets(Pageable snippets);

    /** GET SNIPPETS BY URL */
    SnippetResponseDto getSnippetByUrl(String url);

    /** GET SNIPPETS BY CATEGORY */
    SnippetResponseDto getSnippetByCategory(String category);

    /** UPDATE AN EXISTING SNIPPET */
    SnippetResponseDto updateSnippetByUrl(String url, SnippetRequestDto snippet);

    /** DELETE SNIPPET */
    void deleteSnippetByUrl(String url);
}
