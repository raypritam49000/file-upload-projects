package com.aggregation.rest.api.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class CsvGeneratorUtil<T> {

    private final List<Function<T, Object>> fieldExtractors;

    public CsvGeneratorUtil(List<Function<T, Object>> fieldExtractors) {
        this.fieldExtractors = fieldExtractors;
    }

    public byte[] generateCsv(String csvHeader,List<T> items) {

        StringBuilder csvContent = new StringBuilder();
        csvContent.append(csvHeader);
        csvContent.append("\n");

        for (T item : items) {
            for (Function<T, Object> fieldExtractor : fieldExtractors) {
                csvContent.append(fieldExtractor.apply(item)).append(",");
            }
            csvContent.setLength(csvContent.length() - 1);
            csvContent.append("\n");
        }

        return csvContent.toString().getBytes();
    }
}