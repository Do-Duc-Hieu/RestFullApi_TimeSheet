package com.example.devTimesheet.entity;
import com.example.devTimesheet.mapper.YearMonthAttributeConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

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
