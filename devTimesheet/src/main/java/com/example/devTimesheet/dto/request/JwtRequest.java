package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest {
    @NotBlank(message = "Username must not be blank")
    String username;

    @NotBlank(message = "Password must not be blank")
    String password;
}
