package com.example.devTimesheet.dto.request;

import com.example.devTimesheet.entity.User;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

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
