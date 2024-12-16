package com.example.devTimesheet.service;

import java.time.YearMonth;
import java.util.List;

import com.example.devTimesheet.dto.request.PunishmentRequest;
import com.example.devTimesheet.dto.respon.PunishmentRespon;

public interface PunishmentService {

    //    PunishmentRespon createPunishment(PunishmentRequest request);

    void createPunishmentSchedule();

    List<PunishmentRespon> findAllPunishment();

    List<PunishmentRespon> getPunishmentByUserAndTime(Integer idUser, YearMonth time);

    PunishmentRespon updatePunishment(Integer idPunishment, PunishmentRequest request);

    void deletePunishment(Integer idPunishment);
}
