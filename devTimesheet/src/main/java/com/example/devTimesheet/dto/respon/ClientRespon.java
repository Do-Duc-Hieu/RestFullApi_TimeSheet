package com.example.devTimesheet.dto.respon;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientRespon {
    int id;
    String nameClient;
    String email;
    String address;
    List<ProjectRespon> projectRespons;
}
