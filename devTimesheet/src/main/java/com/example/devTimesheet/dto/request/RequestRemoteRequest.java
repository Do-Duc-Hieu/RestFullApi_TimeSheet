package com.example.devTimesheet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestRemoteRequest extends RequestTypeRequest{
    String note;
    @Builder
    public RequestRemoteRequest(@JsonProperty("types") String types, String type, String note) {
        super(types, type);
        this.note = note;
    }
}
