package com.example.devTimesheet.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "punishment")
public class Punishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDate date;
    String complain;
    int punishmentMoney;
    String complainReply;
    String editedBy;
    String note;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
