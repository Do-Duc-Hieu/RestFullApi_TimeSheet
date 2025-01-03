package com.example.devTimesheet.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.*;

import com.example.devTimesheet.mapper.LocalTimeListAttributeConverter;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
