package com.example.devTimesheet.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkTimeRequest {
    @NotNull(message = "Morning start time must not be null")
    LocalTime morningStartTime;

    @NotNull(message = "Morning end time must not be null")
    LocalTime morningEndTime;

    @NotNull(message = "Afternoon start time must not be null")
    LocalTime afternoonStartTime;

    @NotNull(message = "Afternoon end time must not be null")
    LocalTime afternoonEndTime;
}
