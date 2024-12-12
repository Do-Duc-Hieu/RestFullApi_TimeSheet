package com.example.devTimesheet.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamRequest {
    @NotBlank(message = "Team name must not be blank")
    String nameTeam;

    @NotEmpty(message = "User positions list must not be empty")
    List<UserPositionRequest> userPositions;
}
