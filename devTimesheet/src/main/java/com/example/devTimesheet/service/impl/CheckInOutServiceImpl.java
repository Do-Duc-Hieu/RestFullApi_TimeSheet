package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.dto.respon.CheckInOutRespon;
import com.example.devTimesheet.entity.CheckInOut;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.CheckInOutMapper;
import com.example.devTimesheet.repository.CheckInOutRepository;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.service.CheckInOutService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckInOutServiceImpl implements CheckInOutService {
    CheckInOutRepository checkInOutRepository;
    UserRepository userRepository;
    CheckInOutMapper checkInOutMapper;
    @Override
    public CheckInOutRespon createCheckInOut(String request) {
        List<String> s = List.of(request.split("-"));
        int id = Integer.parseInt(s.get(0));
        LocalTime time = LocalTime.parse(s.get(1));
        CheckInOutRespon checkInOutRespon = new CheckInOutRespon();
        List<LocalTime> listCheckInOut = new ArrayList<>();
        List<CheckInOut> checkInOuts = checkInOutRepository.findByUserIdAndDate(id, LocalDate.now());
        if(checkInOuts.isEmpty()){
            listCheckInOut.add(time);
            CheckInOut checkInOut = CheckInOut.builder()
                    .date(LocalDate.now())
                    .user(userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)))
                    .checkInOuts(listCheckInOut)
                    .build();
            checkInOutRepository.save(checkInOut);
            return checkInOutMapper.toCheckInOutRespon(checkInOut);
        }else{
            CheckInOut checkInOut = checkInOuts.get(0);
            listCheckInOut = checkInOut.getCheckInOuts();
            listCheckInOut.add(time);
            checkInOut.setCheckInOuts(listCheckInOut);
            checkInOutRepository.save(checkInOut);
            return checkInOutMapper.toCheckInOutRespon(checkInOut);
        }
    }
}
