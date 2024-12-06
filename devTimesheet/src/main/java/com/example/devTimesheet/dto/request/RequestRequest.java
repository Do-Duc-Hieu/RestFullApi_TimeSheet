package com.example.devTimesheet.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;

    int idStatus;
    RequestTypeRequest requestType;
}
