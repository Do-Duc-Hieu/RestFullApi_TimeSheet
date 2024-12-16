package com.example.devTimesheet.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nameStatus;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "status",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<Request> requests = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "status",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<TimeSheet> timeSheets = new ArrayList<>();
}
