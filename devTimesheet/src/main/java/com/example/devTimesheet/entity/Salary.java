package com.example.devTimesheet.entity;

import java.time.YearMonth;

import jakarta.persistence.*;

import com.example.devTimesheet.mapper.YearMonthAttributeConverter;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Convert(converter = YearMonthAttributeConverter.class)
    YearMonth time;

    int totalFine;
    int salary;
    int remainSalary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
