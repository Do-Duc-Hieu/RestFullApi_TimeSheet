package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaveTypeRequest {
    @NotBlank(message = "Name type must not be blank")
    String nameType;

    @Min(value = 0, message = "Day off must be a non-negative number")
    int dayOff;
}
