package com.example.devTimesheet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nameBranch;
    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<User> users = new ArrayList<>();
}
