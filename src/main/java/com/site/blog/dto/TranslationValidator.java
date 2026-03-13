package com.site.blog.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TranslationValidator implements ConstraintValidator<ValidTranslation, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true; // Let @NotBlank handle null/empty
        }

        try {
            JsonNode rootNode = objectMapper.readTree(value);

            if (!rootNode.isObject()) {
                return false; // Must be a JSON object
            }

            // Iterate over language keys (e.g., "en", "es")
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                JsonNode langNode = field.getValue();

                // Each language node must be an object
                if (!langNode.isObject()) {
                    return false;
                }

                // Check for required fields "title" and "content"
                if (!langNode.has("title") || !langNode.get("title").isTextual() ||
                    !langNode.has("content") || !langNode.get("content").isTextual()) {
                    return false;
                }
            }

            return true;

        } catch (IOException e) {
            return false; // Invalid JSON format
        }
    }
}
