package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.PunishmentRequest;
import com.example.devTimesheet.dto.respon.PunishmentRespon;
import com.example.devTimesheet.entity.Punishment;

@Mapper(componentModel = "spring")
public interface PunishmentMapper {
    Punishment toPunishment(PunishmentRequest request);

    void updatePunishment(@MappingTarget Punishment punishment, PunishmentRequest request);

    @Mapping(target = "userRespon", source = "user")
    PunishmentRespon toPunishmentRespon(Punishment punishment);
}
