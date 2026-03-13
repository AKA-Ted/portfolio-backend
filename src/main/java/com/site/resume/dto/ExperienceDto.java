package com.site.resume.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExperienceDto {
    @NotBlank(message = "CV info cannot be empty")
    private String cvInfo;

    @NotBlank(message = "CV language cannot be empty")
    private String cvLang;
}
