package com.example.devTimesheet.dto.respon;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String note;
    UserRespon userRespon;
}
