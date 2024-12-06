package com.example.devTimesheet.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.devTimesheet.dto.request.PunishmentRequest;
import com.example.devTimesheet.dto.respon.PunishmentRespon;
import com.example.devTimesheet.entity.Punishment;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.PunishmentMapper;
import com.example.devTimesheet.repository.PunishmentRepository;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.service.PunishmentService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PunishmentServiceImpl implements PunishmentService {
    PunishmentRepository punishmentRepository;
    UserRepository userRepository;
    PunishmentMapper punishmentMapper;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public PunishmentRespon createPunishment(PunishmentRequest request) {

        Punishment punishment = punishmentMapper.toPunishment(request);
        punishment.setDate(LocalDate.now().minusDays(1));
        User user = (User) userRepository
                .findUserByUsername(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        punishment.setUser(user);
        punishment.setPunishmentMoney(handleFines(punishment));
        punishment.setComplainReply(handleComplainRely(punishment));
        punishmentRepository.save(punishment);
        return punishmentMapper.toPunishmentRespon(punishment);
    }

    @Override
    @Transactional
    public void createPunishmentSchedule() {

        List<User> users = userRepository.findAll();
        List<Punishment> punishments = new ArrayList<>();
        executorService.submit(() -> {
            for (User user : users) {

                Punishment punishment = Punishment.builder()
                        .date(LocalDate.now().minusDays(1))
                        .complainReply("")
                        .user(user)
                        .build();
                punishment.setPunishmentMoney(handleFines(punishment));
                punishment.setComplainReply(handleComplainRely(punishment));
                punishments.add(punishment);
            }
            punishmentRepository.saveAll(punishments);
        });
    }

    @Override
    public List<PunishmentRespon> findAllPunishment() {
        List<PunishmentRespon> punishmentRespons = new ArrayList<>();
        List<Punishment> punishments = punishmentRepository.findAll();
        punishments.forEach(punishment -> punishmentRespons.add(punishmentMapper.toPunishmentRespon(punishment)));

        return punishmentRespons;
    }

    @Override
    public PunishmentRespon updatePunishment(Integer idPunishment, PunishmentRequest request) {
        Punishment punishment = punishmentRepository
                .findById(idPunishment)
                .orElseThrow(() -> new RuntimeException("Punishment not found"));
        punishment.setEditedBy(request.getEditedBy());
        punishment.setComplain(request.getComplain());
        punishmentMapper.updatePunishment(punishment, request);
        return punishmentMapper.toPunishmentRespon(punishmentRepository.save(punishment));
    }

    @Override
    public void deletePunishment(Integer idPunishment) {
        punishmentRepository.deleteById(idPunishment);
    }

    public int handleFines(Punishment punishment) {
        int moneyFines = 10;
        return moneyFines;
    }

    public String handleComplainRely(Punishment punishment) {
        String compaplainRely = punishment.getComplainReply();

        return compaplainRely;
    }

    private LocalTime generateRandomTime(LocalTime startTime, LocalTime endTime) {
        long startSeconds = startTime.toSecondOfDay();
        long endSeconds = endTime.toSecondOfDay();
        long randomTime = ThreadLocalRandom.current().nextLong(startSeconds, endSeconds + 1);
        return LocalTime.ofSecondOfDay(randomTime);
    }
}
