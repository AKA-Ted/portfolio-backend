package com.site.blog.repository;

import com.site.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * 1. CREATE/UPDATE: Post save (Post post)
     * - If 'post' has NO an ID, preform create.
     * - If 'post' has an ID, perform an UPDATE.
     * 2. READ (1): Optional<Post> findById(Long id);
     * 3. READ (M): List<Post> findAll();
     * 4. DELETE: void deleteById(Long id);
     * 5. OTHERS: long count();, boolean existsById(Long id);
     * *
     * NEW METHOD
     * Find a post using its ‘url’ field, which is unique.
     */
    Optional<Post> findByUrl(String url);

    List<Post> findByTranslationContainingIgnoreCase(String translation);
}
