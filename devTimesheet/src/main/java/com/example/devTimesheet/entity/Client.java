package com.example.devTimesheet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nameClient;
    String email;
    String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<Project> projects;
}
