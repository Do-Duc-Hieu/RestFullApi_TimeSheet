package com.example.devTimesheet.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "workTime")
public class WorkTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalTime morningStartTime;
    LocalTime morningEndTime;
    LocalTime afternoonStartTime;
    LocalTime afternoonEndTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workTime",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<User> users = new ArrayList<>();
}
