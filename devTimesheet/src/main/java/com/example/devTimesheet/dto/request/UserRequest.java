package com.example.devTimesheet.dto.request;

import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.entity.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "Name must not be blank")
    String name;

    @NotBlank(message = "Sex must not be blank")
    String sex;

    @NotBlank(message = "User type must not be blank")
    String usertype;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be blank")
    String email;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, message = "Username must be at least 3 characters")
    String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;

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
