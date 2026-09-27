package com.site.snippet.repository;

import com.site.snippet.model.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
    Optional<Snippet> findByUrl(String url);
    Optional<Snippet> findByCategory(String category);
}
