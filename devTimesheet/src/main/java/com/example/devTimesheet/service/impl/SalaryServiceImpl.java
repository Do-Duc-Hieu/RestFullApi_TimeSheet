package com.example.devTimesheet.service.impl;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.respon.SalaryRespon;
import com.example.devTimesheet.entity.*;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.SalaryMapper;
import com.example.devTimesheet.repository.*;
import com.example.devTimesheet.service.SalaryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SalaryServiceImpl implements SalaryService {
    SalaryRepository salaryRepository;
    SalaryMapper salaryMapper;
    UserRepository userRepository;
    PunishmentRepository punishmentRepository;

    @Override
    public List<SalaryRespon> timekeeping(YearMonth time) {
        List<Salary> salaries = new ArrayList<>();
        List<User> users = userRepository.findAll();
        List<SalaryRespon> salaryRespons = new ArrayList<>();
        users.forEach(user -> {
            List<Punishment> punishments =
                    punishmentRepository.findByUserIdAndYearMonth(user.getId(), time.getYear(), time.getMonthValue());
            Salary salary = Salary.builder()
                    .salary(user.getSalary())
                    .time(time)
                    .totalFine(0)
                    .remainSalary(user.getSalary())
                    .user(user)
                    .build();
            punishments.forEach(punishment -> {
                salary.setTotalFine(salary.getTotalFine() + punishment.getPunishmentMoney());
            });
            salary.setRemainSalary(salary.getSalary() - salary.getTotalFine());
            if (salary.getRemainSalary() < 0) salary.setRemainSalary(0);
            salaries.add(salary);
            salaryRespons.add(salaryMapper.toSalaryRespon(salary));
        });
        salaryRepository.deleteByTime(time);
        salaryRepository.saveAll(salaries);
        return salaryRespons;
    }

    @Override
    public SalaryRespon getSalary(Integer id) {
        return salaryMapper.toSalaryRespon(
                salaryRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    @Override
    public List<SalaryRespon> findAllSalary() {
        List<SalaryRespon> salaryRespons = new ArrayList<>();
        List<Salary> salaries = salaryRepository.findAll();
        salaries.forEach(salary -> salaryRespons.add(salaryMapper.toSalaryRespon(salary)));

        return salaryRespons;
    }

    //    @Override
    //    public RoleRespon updateRole(Integer idRole, RoleRequest request) {
    //        Role role = roleRepository.findById(idRole)
    //                .orElseThrow(()-> new RuntimeException("Role not found"));
    //        List<Permission> permissions = new ArrayList<>();
    //        request.getNamePermissions().forEach(
    //                namePermission -> permissions.add((Permission) permissionRepository
    //                        .findPermissionByNamePermission(namePermission)
    //                        .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)))
    //        );
    //        roleMapper.updateRole(role, request);
    //        role.setPermissions(permissions);
    //        return roleMapper.toRoleRespon(roleRepository.save(role));
    //    }
    //
    //    @Override
    //    public void deleteRole(Integer idRole){
    //        roleRepository.deleteById(idRole);
    //    }
}
