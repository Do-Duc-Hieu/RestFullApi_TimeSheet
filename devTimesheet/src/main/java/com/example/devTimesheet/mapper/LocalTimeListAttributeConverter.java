package com.example.devTimesheet.mapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Converter(autoApply = true) // Tự động áp dụng khi gặp List<LocalTime>
public class LocalTimeListAttributeConverter implements AttributeConverter<List<LocalTime>, String> {

    private static final String DELIMITER = "-"; // Ký tự phân tách

    @Override
    public String convertToDatabaseColumn(List<LocalTime> localTimes) {
        if (localTimes == null || localTimes.isEmpty()) {
            return null;
        }
        // Nối các LocalTime thành chuỗi phân cách bằng DELIMITER
        return localTimes.stream()
                .map(LocalTime::toString)
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public List<LocalTime> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        // Tách chuỗi và chuyển về danh sách LocalTime
        return Arrays.stream(dbData.split(DELIMITER))
                .map(LocalTime::parse)
                .collect(Collectors.toList());
    }
}