package com.example.devTimesheet.dto.respon;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestWorkTimeRespon extends RequestTypeRespon {
    LocalTime morningStartTime;
    LocalTime morningEndTime;
    LocalTime afternoonStartTime;
    LocalTime afternoonEndTime;
    LocalDate dayApply;
}
