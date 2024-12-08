package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.PunishmentRequest;
import com.example.devTimesheet.dto.respon.PunishmentRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PunishmentService {

//    PunishmentRespon createPunishment(PunishmentRequest request);

    void createPunishmentSchedule();

    List<PunishmentRespon> findAllPunishment();

    PunishmentRespon updatePunishment(Integer idPunishment, PunishmentRequest request);

    void deletePunishment(Integer idPunishment);
}
