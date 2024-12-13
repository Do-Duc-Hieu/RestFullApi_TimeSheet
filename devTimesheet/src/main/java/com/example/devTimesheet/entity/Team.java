package com.example.devTimesheet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nameTeam;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team",
            cascade = {CascadeType.ALL})
    List<UserPosition> userPositions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<Project> project;
}
