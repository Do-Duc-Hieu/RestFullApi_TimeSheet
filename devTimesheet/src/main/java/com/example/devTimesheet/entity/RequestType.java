package com.example.devTimesheet.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "request_type")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "requestType")
public abstract class RequestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne(mappedBy = "requestType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Request request;
}
