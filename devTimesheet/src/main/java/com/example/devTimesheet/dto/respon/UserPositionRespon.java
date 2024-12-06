package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.entity.Position;
import com.example.devTimesheet.entity.Team;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPositionRespon {
    int id;
    UserRespon userRespon;
    PositionRespon positionRespon;
}
