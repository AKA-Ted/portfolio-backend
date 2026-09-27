package com.site.snippet.service;

import com.site.global.exception.ResourceConflictException;
import com.site.snippet.dto.SnippetRequestDto;
import com.site.snippet.dto.SnippetResponseDto;
import com.site.snippet.mapper.SnippetMapper;
import com.site.snippet.model.Snippet;
import com.site.snippet.repository.SnippetRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SnippetServiceImpl implements SnippetService {
    private final SnippetRepository snippetRepository;

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public SnippetResponseDto createSnippet(SnippetRequestDto createSnippet) {
        checkIfUrlExists(createSnippet.getUrl());

        Snippet snippet = new Snippet();
        SnippetMapper.updateSnippetFromRequestDto(createSnippet, snippet);

        Snippet savedSnippet = snippetRepository.save(snippet);
        return SnippetMapper.toResponseDto(savedSnippet);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SnippetResponseDto> getAllSnippets(Pageable snippets) {
       return snippetRepository.findAll(snippets)
               .map(SnippetMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SnippetResponseDto getSnippetByUrl(String url) {
        Snippet snippet = snippetRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceConflictException("Snippet", "url", url));
        return SnippetMapper.toResponseDto(snippet);
    }

    @Override
    @Transactional(readOnly = true)
    public SnippetResponseDto getSnippetByCategory(String category) {
        Snippet snippet = snippetRepository.findByCategory(category)
                .orElseThrow(() -> new ResourceConflictException("Snippet", "category", category));
        return SnippetMapper.toResponseDto(snippet);
    }

    @Override
    @Transactional
    public SnippetResponseDto updateSnippetByUrl(String url, SnippetRequestDto updateSnippetDto) {
        Snippet existingSnippet = snippetRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceConflictException("Snippet", "url", url));

        String newUrl = updateSnippetDto.getUrl();
        if(!existingSnippet.getUrl().equals(newUrl)) {
            checkIfUrlExists(newUrl);
        }

        SnippetMapper.updateSnippetFromRequestDto(updateSnippetDto, existingSnippet);

        Snippet updatedSnippet = snippetRepository.save(existingSnippet);
        return SnippetMapper.toResponseDto(updatedSnippet);
    }

    @Override
    @Transactional
    public void deleteSnippetByUrl(String url) {
        Snippet snippet = snippetRepository.findByUrl(url)
                .orElseThrow(() -> new ResourceConflictException("Snippet", "url", url));
        snippetRepository.delete(snippet);
    }

    private void checkIfUrlExists(String url) {
        Optional<Snippet> snippetByUrl = snippetRepository.findByUrl(url);
        if (snippetByUrl.isPresent()) throw new ResourceConflictException("Snippet", "url", url);
    }
}
