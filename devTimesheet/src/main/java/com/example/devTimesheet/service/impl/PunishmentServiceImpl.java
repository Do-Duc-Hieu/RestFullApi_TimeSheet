package com.example.devTimesheet.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import com.example.devTimesheet.entity.*;
import com.example.devTimesheet.repository.CheckInOutRepository;
import com.example.devTimesheet.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.devTimesheet.dto.request.PunishmentRequest;
import com.example.devTimesheet.dto.respon.PunishmentRespon;
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
    CheckInOutRepository checkInOutRepository;
    RequestRepository requestRepository;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

//    @Override
//    public PunishmentRespon createPunishment(PunishmentRequest request) {
//
//        Punishment punishment = punishmentMapper.toPunishment(request);
//        punishment.setDate(LocalDate.now().minusDays(1));
//        User user = (User) userRepository
//                .findUserByUsername(request.getUserName())
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        punishment.setUser(user);
//        punishment.setCheckInRegister(user.getWorkTime().getMorningStartTime());
//        punishment.setCheckOutRegister(user.getWorkTime().getAfternoonEndTime());
//        punishment.setPunishmentMoney(handleFines(punishment, punish));
//        punishment.setComplainReply(handleComplainRely(punishment));
//        punishmentRepository.save(punishment);
//        return punishmentMapper.toPunishmentRespon(punishment);
//    }

    @Override
    @Transactional
    public void createPunishmentSchedule() {

        List<User> users = userRepository.findAll();
        List<Punishment> punishments = new ArrayList<>();
        LocalDate date = LocalDate.now().minusDays(1);
        executorService.submit(() -> {
            for (User user : users) {
                Punishment punishment = Punishment.builder()
                        .date(date)
                        .checkInRegister(user.getWorkTime().getMorningStartTime())
                        .checkOutRegister(user.getWorkTime().getAfternoonEndTime())
                        .punishmentMoney(0)
                        .complainReply("")
                        .user(user)
                        .build();
                List<CheckInOut> checkInOuts = checkInOutRepository.findByUsernameAndDate(user.getUsername(), date);

                // Thực hiện điểm danh
                if(!checkInOuts.isEmpty()){
                    CheckInOut checkInOut = checkInOuts.get(0);
                    List<LocalTime> listCheckIn = checkInOut.getCheckInOuts();
                    punishment.setCheckIn(checkInOut.getCheckInOuts().get(0));
                    punishment.setCheckOut(checkInOut.getCheckInOuts().get(checkInOut.getCheckInOuts().size()-1));

                    //Chưa check out lần cuối
                    if(listCheckIn.size() % 2 != 0){
                        listCheckIn.add(checkInOut.getCheckInOuts().get(checkInOut.getCheckInOuts().size()-1));
                        punishment.setPunishmentMoney(50);
                        punishment.setComplainReply("Không check out, ");
                    }

                    punishment.setPunishmentMoney(handleFines(punishment, listCheckIn));
                    punishment.setComplainReply(handleComplainRely(punishment, listCheckIn));
                }

                else{ //không thực hiện điểm danh
                    List<Request> requests = requestRepository.findAllOffRequestsByUserAndPending(
                            punishment.getUser().getUsername()
                            , "Approved"
                            , punishment.getDate().minusDays(15)
                            , punishment.getDate());
                    AtomicReference<Integer> flag = new AtomicReference<>(0);
                    requests.forEach(
                            request -> {
                                if(request.getRequestType() instanceof RequestOff){
                                    RequestOff requestOff = (RequestOff) request.getRequestType();
                                    LocalDate date1 = requestOff.getRequest().getDate();
                                    if(punishment.getDate().until(
                                            requestOff.getRequest().getDate().plusDays(requestOff.getLeaveType().getDayOff())
                                            , ChronoUnit.DAYS)>=0){
                                        flag.set(1);
                                    }
                                }
                            }
                    );
                    if (flag.get()==0){
                        punishment.setPunishmentMoney(punishment.getPunishmentMoney() + 500 + punishment.getUser().getSalary()/30);
                        punishment.setComplainReply("Không đi làm, ");
                    }
                }
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

    public int handleFines(Punishment punishment, List<LocalTime> listCheckIn) {
        int moneyFines = punishment.getPunishmentMoney();
        LocalTime checkIn = punishment.getCheckIn();
        LocalTime checkOut = punishment.getCheckOut();
        LocalTime checkInRegis = punishment.getCheckInRegister();
        LocalTime checkOutRegis = punishment.getCheckOutRegister();

        //Làm thiếu giờ
        if (checkInRegis.until(checkOutRegis, ChronoUnit.MINUTES) - calculateTotalMinutes(listCheckIn) > 0) {
            moneyFines += 50;
        }
        // đến muộn
        if (checkInRegis.until(checkIn, ChronoUnit.MINUTES) > 15) {
            List<Request> requests = requestRepository.findAllLastRequestsByUserAndDate(
                    punishment.getUser().getUsername(), "Approved", punishment.getDate());
            AtomicReference<Integer> flag = new AtomicReference<>(0);
            float timeLast = checkInRegis.until(checkIn, ChronoUnit.MINUTES);
            requests.forEach(
                    request -> {
                        if(request.getRequestType() instanceof RequestLast){
                            RequestLast requestLast = (RequestLast) request.getRequestType();
                            if(checkInRegis.until(checkIn, ChronoUnit.MINUTES) < requestLast.getHour()){
                                flag.set(1);
                            }
                        }
                    }
            );
            if (flag.get()==0) moneyFines+=20;
        }
        return moneyFines;
    }

    public String handleComplainRely(Punishment punishment, List<LocalTime> listCheckIn) {
        String compaplainRely = punishment.getComplainReply();
        LocalTime checkIn = punishment.getCheckIn();
        LocalTime checkOut = punishment.getCheckOut();
        LocalTime checkInRegis = punishment.getCheckInRegister();
        LocalTime checkOutRegis = punishment.getCheckOutRegister();

        if (checkInRegis.until(checkOutRegis, ChronoUnit.MINUTES) - calculateTotalMinutes(listCheckIn) > 0) {
            compaplainRely += "Làm thiếu giờ, ";
        }
        // đến muộn
        if (checkInRegis.until(checkIn, ChronoUnit.MINUTES) > 15) {
            List<Request> requests = requestRepository.findAllLastRequestsByUserAndDate(
                    punishment.getUser().getUsername(), "Approved", punishment.getDate());
            AtomicReference<Integer> flag = new AtomicReference<>(0);
            float timeLast = checkInRegis.until(checkIn, ChronoUnit.MINUTES);
            requests.forEach(
                    request -> {
                        if(request.getRequestType() instanceof RequestLast){
                            RequestLast requestLast = (RequestLast) request.getRequestType();
                            if(checkInRegis.until(checkIn, ChronoUnit.MINUTES) < requestLast.getHour()){
                                flag.set(1);
                            }
                        }
                    }
            );
            if (flag.get()==0) compaplainRely+="Đi làm muộn, ";
        }
        return compaplainRely;
    }

    public static long calculateTotalMinutes(List<LocalTime> checkInOuts) {
        long totalMinutes = 0;

        // Duyệt qua danh sách theo từng cặp check-in, check-out
        for (int i = 0; i < checkInOuts.size(); i += 2) {
            LocalTime checkIn = checkInOuts.get(i);     // Lấy thời gian check-in
            LocalTime checkOut = checkInOuts.get(i + 1); // Lấy thời gian check-out

            // Tính thời gian giữa check-in và check-out
            long minutes = checkIn.until(checkOut, ChronoUnit.MINUTES);
            totalMinutes += minutes;
        }

        return totalMinutes; // Tổng số phút làm việc
    }
}