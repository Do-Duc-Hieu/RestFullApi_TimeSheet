package com.example.devTimesheet.dto.respon;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamRespon {
    int id;
    String nameTeam;
    List<UserPositionRespon> userPositionRespons;
}
