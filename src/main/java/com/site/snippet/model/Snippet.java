package com.site.snippet.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "snippets")
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE snippets SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String command;

    @Column(nullable = false, unique = true)
    private String url;

    @JsonRawValue
    @Column(nullable = false, columnDefinition = "TEXT")
    private String translation;

    @Column(nullable = false)
    private Visualizer visualizer;

    @JsonRawValue
    @Column(nullable = false, columnDefinition = "TEXT")
    private String io;

    @Column(name = "is_published", nullable = false)
    private boolean isPublished = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

}
