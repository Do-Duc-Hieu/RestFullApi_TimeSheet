package com.example.devTimesheet.dto.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
