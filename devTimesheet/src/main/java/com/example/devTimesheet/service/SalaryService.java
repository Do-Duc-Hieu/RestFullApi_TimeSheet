package com.example.devTimesheet.service;
import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.respon.RoleRespon;
import com.example.devTimesheet.dto.respon.SalaryRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.YearMonth;
import java.util.List;
public interface SalaryService {
    List<SalaryRespon> timekeeping(YearMonth time);

    SalaryRespon getSalary(Integer id);

    List<SalaryRespon> findAllSalary();

    List<SalaryRespon> getSalaryByUser(String username);
}
