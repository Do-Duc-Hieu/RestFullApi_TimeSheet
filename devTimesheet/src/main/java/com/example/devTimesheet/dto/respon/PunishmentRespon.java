package com.example.devTimesheet.dto.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PunishmentRespon {
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
    UserRespon userRespon;
}
