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
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String namePosition;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "position",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<UserPosition> userPositions;
}
