package com.example.devTimesheet.dto.respon;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ApiRespon<T> {
    int code = 200;
    String message;
    T result;
}
