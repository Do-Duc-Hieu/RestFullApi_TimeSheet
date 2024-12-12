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
    LocalTime checkIn;
    LocalTime checkOut;
    LocalTime checkInRegister;
    LocalTime checkOutRegister;

    @ManyToOne
    @JoinColumn(name    = "user_id")
    User user;
}
