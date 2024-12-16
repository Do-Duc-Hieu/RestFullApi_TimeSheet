package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientRequest {
    @NotBlank(message = "Client name must not be blank")
    String nameClient;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be blank")
    String email;

    @NotBlank(message = "Address must not be blank")
    String address;
}
