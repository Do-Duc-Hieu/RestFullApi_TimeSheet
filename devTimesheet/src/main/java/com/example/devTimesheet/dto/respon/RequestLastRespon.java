package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.request.RequestTypeRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestLastRespon extends RequestTypeRespon {
    String note;
    float hour;
}