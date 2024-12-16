package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.devTimesheet.dto.respon.SalaryRespon;
import com.example.devTimesheet.entity.Salary;

@Mapper(componentModel = "spring")
public interface SalaryMapper {
    // Salary toSalary(StalaryRequest request);
    // void updateSalary(@MappingTarget Salary salary, SalaryRequest request);
    @Mapping(target = "userRespon", source = "user")
    SalaryRespon toSalaryRespon(Salary salary);
}
