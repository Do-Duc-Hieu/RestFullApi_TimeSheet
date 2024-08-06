package com.example.devTimesheet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DiscriminatorValue("OFF")
public class RequestOff extends RequestType {
    String note;

    @ManyToOne
    @JoinColumn(name = "leaveType_id")
    LeaveType leaveType;
}
