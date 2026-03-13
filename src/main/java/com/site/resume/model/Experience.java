package com.site.resume.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "experience")
@Data
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonRawValue
    @Column(name = "cv_info", columnDefinition = "TEXT")
    private String cvInfo;

    @Column(nullable = false, unique = true)
    private String cvLang;
}
