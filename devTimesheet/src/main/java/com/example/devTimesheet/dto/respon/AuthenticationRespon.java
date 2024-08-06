package com.example.devTimesheet.dto.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRespon {
    String token;
    String tokenRefresh;
    boolean authenticated;
}
