package com.example.devTimesheet.dto.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeaveTypeRespon {
    int id;
    String nameType;
    int dayOff;
    //   List<RequestOff> requestOffs;
}
