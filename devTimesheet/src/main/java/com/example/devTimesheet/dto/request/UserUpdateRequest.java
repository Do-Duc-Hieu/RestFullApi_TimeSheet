package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.example.devTimesheet.entity.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @NotBlank(message = "Name must not be blank")
    String name;

    @NotBlank(message = "Sex must not be blank")
    String sex;

    @NotBlank(message = "User type must not be blank")
    String usertype;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be blank")
    String email;

    @NotBlank(message = "Address must not be blank")
    String address;

    String phone;
    String bank;
    String bankAccount;
    String taxCode;

    @NotNull(message = "Salary must not be null")
    int salary;

    @NotNull(message = "Role must not be null")
    RoleRequest role;

    @NotNull(message = "Branch must not be null")
    BranchRequest branch;
}
