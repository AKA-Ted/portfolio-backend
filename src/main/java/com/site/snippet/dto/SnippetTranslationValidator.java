package com.site.snippet.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class SnippetTranslationValidator implements ConstraintValidator<ValidSnippetTranslation, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }

        try {
            JsonNode rootNode = objectMapper.readTree(value);

            if (!rootNode.isObject()) {
                return false;
            }

            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                JsonNode langNode = field.getValue();

                if (!langNode.isObject()) {
                    return false;
                }

                if (!langNode.has("description") || !langNode.get("description").isTextual() ||
                    !langNode.has("code") || !langNode.get("code").isTextual()) {
                    return false;
                }
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
