package com.example.devTimesheet.dto.respon;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.mapper.LocalTimeListAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
