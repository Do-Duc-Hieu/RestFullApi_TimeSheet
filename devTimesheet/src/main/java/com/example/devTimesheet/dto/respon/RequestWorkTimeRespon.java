package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.request.RequestTypeRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

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
