package com.site.blog.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "blog")
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE blog SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;

    @JsonRawValue
    @Column(nullable = false, columnDefinition = "TEXT")
    private String translation;

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
