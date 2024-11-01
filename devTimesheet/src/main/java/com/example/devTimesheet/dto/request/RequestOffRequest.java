package com.example.devTimesheet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestOffRequest extends RequestTypeRequest {
    String note;
    LeaveTypeRequest leaveTypeRequest;

    public RequestOffRequest(
            @JsonProperty("types") String types, String type, String note, LeaveTypeRequest leaveTypeRequest) {
        super(types, type);
        this.note = note;
        this.leaveTypeRequest = leaveTypeRequest;
    }
}
