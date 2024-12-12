package com.example.devTimesheet.dto.respon;

import java.time.YearMonth;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class SalaryRespon {
    int id;
    YearMonth time;
    int totalFine;
    int salary;
    int remainSalary;
    UserRespon userRespon;
}
