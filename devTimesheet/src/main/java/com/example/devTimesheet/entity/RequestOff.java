package com.example.devTimesheet.entity;

import java.io.File;

import jakarta.persistence.*;

import com.example.devTimesheet.mapper.FileImageAttributeConverter;

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

    @Convert(converter = FileImageAttributeConverter.class)
    File Image;

    @ManyToOne
    @JoinColumn(name = "leaveType_id")
    LeaveType leaveType;
}
