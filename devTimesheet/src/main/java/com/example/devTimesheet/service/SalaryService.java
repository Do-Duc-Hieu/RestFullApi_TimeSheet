package com.example.devTimesheet.service;

import java.time.YearMonth;
import java.util.List;

import com.example.devTimesheet.dto.respon.SalaryRespon;

public interface SalaryService {
    List<SalaryRespon> timekeeping(YearMonth time);

    SalaryRespon getSalary(Integer id);

    List<SalaryRespon> findAllSalary();

    List<SalaryRespon> getSalaryByUser(String username);
}
