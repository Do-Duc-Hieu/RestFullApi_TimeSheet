package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.request.LeaveTypeRequest;
import com.example.devTimesheet.dto.request.RequestTypeRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.File;

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
