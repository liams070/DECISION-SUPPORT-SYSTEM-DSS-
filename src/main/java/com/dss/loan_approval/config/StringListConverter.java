package com.dss.loan_approval.config;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting list to JSON string", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isBlank()) {
                return List.of();
            }
            return mapper.readValue(dbData, List.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to list", e);
        }
    }

}
