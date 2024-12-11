package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPositionRequest {
    @NotBlank(message = "User name must not be blank")
    String userName;

    @NotBlank(message = "Position name must not be blank")
    String namePosition;
}
