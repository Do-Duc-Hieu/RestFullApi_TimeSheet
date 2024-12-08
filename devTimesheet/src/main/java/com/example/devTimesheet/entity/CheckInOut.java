package com.example.devTimesheet.entity;
import com.example.devTimesheet.mapper.LocalTimeListAttributeConverter;
import com.example.devTimesheet.mapper.YearMonthAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "checkInOut")
public class CheckInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate date;
    @Convert(converter = LocalTimeListAttributeConverter.class)
    List<LocalTime> checkInOuts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
