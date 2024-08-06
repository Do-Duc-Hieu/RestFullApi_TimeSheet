package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.entity.RequestOff;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

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
