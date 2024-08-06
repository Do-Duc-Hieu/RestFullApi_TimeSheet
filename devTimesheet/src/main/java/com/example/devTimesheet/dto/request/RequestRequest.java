package com.example.devTimesheet.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public  class RequestRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;
    int idStatus;
    RequestTypeRequest requestType;

}
