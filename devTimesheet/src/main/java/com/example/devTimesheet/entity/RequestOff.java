package com.example.devTimesheet.entity;

import com.example.devTimesheet.mapper.FileImageAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.File;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DiscriminatorValue("OFF")
public class RequestOff extends RequestType {
    String note;
    @Convert(converter = FileImageAttributeConverter.class)
    File Image;

    @ManyToOne
    @JoinColumn(name = "leaveType_id")
    LeaveType leaveType;
}
