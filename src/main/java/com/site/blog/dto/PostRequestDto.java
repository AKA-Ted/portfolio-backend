package com.site.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequestDto {
    @NotBlank(message = "The URL can't be empty")
    @Size(max = 255)
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "The URL can only contain lowercase letters, numbers, and hyphens (e.g., ‘my-first-post’).")
    private String url;

    @NotBlank(message = "The content can't be empty")
    @ValidTranslation
    private String translation;

    private boolean isPublished;
}
