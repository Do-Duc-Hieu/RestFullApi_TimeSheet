package com.example.devTimesheet.dto.respon;
import com.example.devTimesheet.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.YearMonth;
import java.util.List;

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
