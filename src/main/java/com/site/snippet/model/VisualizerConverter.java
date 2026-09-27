package com.site.snippet.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class VisualizerConverter implements AttributeConverter<Visualizer, String> {
    @Override
    public String convertToDatabaseColumn(Visualizer attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name().toLowerCase();
    }

    @Override
    public Visualizer convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Visualizer.valueOf(dbData.toUpperCase());
    }
}
