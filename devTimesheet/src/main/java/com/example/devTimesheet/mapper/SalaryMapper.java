package com.example.devTimesheet.mapper;
import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.request.StatusRequest;
import com.example.devTimesheet.dto.respon.SalaryRespon;
import com.example.devTimesheet.entity.Salary;
import com.example.devTimesheet.entity.Status;
import com.example.devTimesheet.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface SalaryMapper {
    //Salary toSalary(StalaryRequest request);
    //void updateSalary(@MappingTarget Salary salary, SalaryRequest request);
    @Mapping(target = "userRespon", source = "user")
    SalaryRespon toSalaryRespon(Salary salary);
}
