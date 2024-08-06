package com.example.devTimesheet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String namePermission;
    @ManyToMany(mappedBy = "permissions",cascade = {CascadeType.DETACH, CascadeType.MERGE
            , CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonBackReference
    List<Role> roles;
}
