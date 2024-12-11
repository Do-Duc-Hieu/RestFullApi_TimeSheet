package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.request.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class RequestTypeRespon {
    String type;
}
