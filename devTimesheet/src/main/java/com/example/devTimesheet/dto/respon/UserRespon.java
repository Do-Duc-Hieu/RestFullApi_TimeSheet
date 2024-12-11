package com.example.devTimesheet.dto.respon;

import java.io.File;

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
    File avatar;
    int salary;
    RoleRespon role;

    BranchRespon branch;

    WorkTimeRespon workTime;
}
