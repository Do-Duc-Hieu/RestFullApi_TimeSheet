package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchRequest {
    @NotBlank(message = "Branch name must not be blank")
    String nameBranch;
}
