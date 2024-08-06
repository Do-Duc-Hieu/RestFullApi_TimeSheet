package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
    @NotBlank(message = "Role name must not be blank")
    String nameRole;

    @NotEmpty(message = "Permissions list must not be empty")
    List<String> namePermissions = new ArrayList<>();
}
