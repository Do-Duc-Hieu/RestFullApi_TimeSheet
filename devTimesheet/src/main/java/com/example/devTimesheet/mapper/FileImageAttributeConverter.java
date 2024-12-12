package com.example.devTimesheet.mapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.File;

@Converter(autoApply = true)
public class FileImageAttributeConverter implements AttributeConverter<File, String>{
    @Override
    public String convertToDatabaseColumn(File file) {
        // Chuyển File thành chuỗi đường dẫn
        return (file != null) ? file.getAbsolutePath() : null;
    }

    @Override
    public File convertToEntityAttribute(String dbData) {
        // Chuyển đường dẫn trong database thành File
        return (dbData != null && !dbData.isEmpty()) ? new File(dbData) : null;
    }
}
