package com.example.devTimesheet.dto.request;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {
    @NotNull
    @NotBlank(message = "Project name must not be blank")
    String nameProject;

    @NotNull(message = "Date must not be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date;

    String note;

    @NotBlank(message = "Client name must not be blank")
    String nameClient;

    @NotBlank(message = "Team name must not be blank")
    String nameTeam;

    @Size(min = 1, message = "At least one task must be specified")
    List<@NotBlank(message = "Task name must not be blank") String> nameTasks;
}
