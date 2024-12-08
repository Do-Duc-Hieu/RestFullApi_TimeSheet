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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int sex;
    String usertype;
    String email;
    String username;
    String password;
    String address;
    String phone;
    String bank;
    String bankAccount;
    String taxCode;
    String avatarUrl;
    int salary;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    Branch branch;

    @ManyToOne
    @JoinColumn(name = "workTime_id")
    WorkTime workTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<Punishment> punishments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<Request> requests;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    List<UserPosition> userPositions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<TimeSheet> timeSheets;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<Salary> salarys;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    , CascadeType.PERSIST, CascadeType.REFRESH})
    List<CheckInOut> checkInOuts;
}
