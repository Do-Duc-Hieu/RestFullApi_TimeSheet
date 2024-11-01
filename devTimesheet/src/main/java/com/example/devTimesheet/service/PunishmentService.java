package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.PunishmentRequest;
import com.example.devTimesheet.dto.respon.PunishmentRespon;

public interface PunishmentService {

    PunishmentRespon createPunishment(PunishmentRequest request);

    void createPunishmentSchedule();

    List<PunishmentRespon> findAllPunishment();

    PunishmentRespon updatePunishment(Integer idPunishment, PunishmentRequest request);

    void deletePunishment(Integer idPunishment);
}
