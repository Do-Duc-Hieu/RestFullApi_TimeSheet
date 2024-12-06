package com.example.devTimesheet.dto.request;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PunishmentRequest {
    String complain;
    String complainReply;
    String editedBy;

    @NotNull(message = "Check-in time must not be null")
    LocalTime checkIn;

    @NotNull(message = "Check-out time must not be null")
    LocalTime checkOut;

    @NotBlank(message = "User name must not be blank")
    String userName;
}
