package com.example.devTimesheet.dto.request;

import com.example.devTimesheet.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

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
