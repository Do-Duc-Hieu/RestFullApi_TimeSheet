package com.example.devTimesheet.dto.respon;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
