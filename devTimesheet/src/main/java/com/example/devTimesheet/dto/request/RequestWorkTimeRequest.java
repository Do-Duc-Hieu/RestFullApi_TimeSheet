package com.example.devTimesheet.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestWorkTimeRequest extends RequestTypeRequest {
    LocalTime morningStartTime;
    LocalTime morningEndTime;
    LocalTime afternoonStartTime;
    LocalTime afternoonEndTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dayApply;

    @Builder
    public RequestWorkTimeRequest(
            @JsonProperty("types") String types,
            String type,
            LocalTime morningStartTime,
            LocalTime morningEndTime,
            LocalTime afternoonStartTime,
            LocalTime afternoonEndTime,
            LocalDate dayApply) {
        super(types, type);
        this.morningStartTime = morningStartTime;
        this.morningEndTime = morningEndTime;
        this.afternoonStartTime = afternoonStartTime;
        this.afternoonEndTime = afternoonEndTime;
        this.dayApply = dayApply;
    }
}
