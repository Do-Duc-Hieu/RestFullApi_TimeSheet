package com.example.devTimesheet.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Date must not be null")
    LocalDate date;

    @NotBlank(message = "Task must not be blank")
    String task;

    String note;

    @Positive(message = "Working time must be positive")
    int workingTime;

    @NotBlank(message = "Type must not be blank")
    String type;

    @NotBlank(message = "Project name must not be blank")
    String nameProject;

    int idStatus;
}
