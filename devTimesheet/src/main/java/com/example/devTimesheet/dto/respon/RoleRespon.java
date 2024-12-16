package com.example.devTimesheet.dto.respon;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class RoleRespon {
    int id;
    String nameRole;

    List<PermissionRespon> permissionRespons;
    List<UserRespon> userRespons;
}
