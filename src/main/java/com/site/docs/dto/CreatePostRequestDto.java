package com.site.docs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePostRequestDto {
    @NotBlank(message = "The title can't be empty")
    @Size(min = 3, max = 255, message = "The title must be between 3 and 255 characters long.")
    private String title;

    @NotBlank(message = "The URL can't be empty")
    @Size(max = 255)
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
            message = "The URL can only contain lowercase letters, numbers, and hyphens (e.g., ‘my-first-post’).")
    private String url;

    @NotBlank(message = "The content can't be empty")
    private String content;

    private boolean isPublished;
}
