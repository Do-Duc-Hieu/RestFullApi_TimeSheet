package com.example.devTimesheet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestLastRequest extends RequestTypeRequest{
    String note;
    float hour;
    @Builder
    public RequestLastRequest(@JsonProperty("types") String types, String type, String note, float hour) {
        super(types, type);
        this.note = note;
        this.hour = hour;
    }
}
