package com.site.snippet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SnippetRequestDto {
    @NotBlank(message = "The URL can't be empty")
    @Size(max = 255)
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "The URL can only contain lowercase letters, numbers, and hyphens (e.g., ‘my-first-post’).")
    private String url;

    @NotBlank(message = "The category can't be empty")
    private String category;

    @NotBlank(message = "Command can't be empty")
    private String command;

    @NotBlank(message = "The content can't be empty")
    @ValidSnippetTranslation
    private String translation;

    @NotBlank(message = "The visualizer can't be empty")
    private String visualizer;

    @NotBlank(message = "The content can't be empty")
    private String io;

    private boolean isPublished;
}
