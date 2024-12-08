package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.request.RequestTypeRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestRespon {
    int id;
    LocalDate date;
    LocalDate createdAt;
    LocalDate lastModifiedAt;
    UserRespon userRespon;
    StatusRespon statusRespon;
    RequestTypeRespon requestTypeRespon;
}
