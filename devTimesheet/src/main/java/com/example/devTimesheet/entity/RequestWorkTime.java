package com.example.devTimesheet.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DiscriminatorValue("WORKTIME")
public class RequestWorkTime  extends RequestType{
    LocalTime morningStartTime;
    LocalTime morningEndTime;
    LocalTime afternoonStartTime;
    LocalTime afternoonEndTime;
    LocalDate dayApply;
}
