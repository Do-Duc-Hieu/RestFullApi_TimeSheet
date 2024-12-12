package com.example.devTimesheet.dto.request;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "types")
@JsonSubTypes({
    @JsonSubTypes.Type(value = RequestRemoteRequest.class, name = "REMOTE"),
    @JsonSubTypes.Type(value = RequestLastRequest.class, name = "LAST"),
    @JsonSubTypes.Type(value = RequestWorkTimeRequest.class, name = "WORKTIME"),
    @JsonSubTypes.Type(value = RequestOffRequest.class, name = "OFF")
})
public abstract class RequestTypeRequest {
    @NotBlank(message = "Type must not be blank")
    String types;

    @NotBlank(message = "Type name must not be blank")
    String type;
}
