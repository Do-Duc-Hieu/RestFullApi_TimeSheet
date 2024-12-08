package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.respon.RoleRespon;
import com.example.devTimesheet.entity.Branch;
import com.example.devTimesheet.entity.WorkTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRespon {
    int id;
    String name;
    String sex;
    String usertype;
    String email;
    String username;
    String address;
    String phone;
    String bank;
    String bankAccount;
    String taxCode;
    String avatarUrl;
    int salary;
    RoleRespon role;

    BranchRespon branch;

    WorkTimeRespon workTime;
}
