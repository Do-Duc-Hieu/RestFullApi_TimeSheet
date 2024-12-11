package com.example.devTimesheet.dto.respon;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckInOutRespon {
    int id;
    LocalDate date;
    List<LocalTime> checkInOuts;
    UserRespon userRespon;
}
