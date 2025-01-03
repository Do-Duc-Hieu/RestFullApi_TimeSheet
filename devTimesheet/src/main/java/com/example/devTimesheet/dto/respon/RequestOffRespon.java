package com.example.devTimesheet.dto.respon;

import java.io.File;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestOffRespon extends RequestTypeRespon {
    String note;
    File Image;
    LeaveTypeRespon leaveTypeRespon;
}
