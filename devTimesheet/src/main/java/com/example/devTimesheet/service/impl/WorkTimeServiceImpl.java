package com.example.devTimesheet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.WorkTimeRequest;
import com.example.devTimesheet.dto.respon.WorkTimeRespon;
import com.example.devTimesheet.entity.WorkTime;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.WorkTimeMapper;
import com.example.devTimesheet.repository.WorkTimeRepository;
import com.example.devTimesheet.service.WorkTimeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkTimeServiceImpl implements WorkTimeService {
    WorkTimeRepository workTimeRepository;
    WorkTimeMapper workTimeMapper;

    @Override
    public WorkTimeRespon createWorkTime(WorkTimeRequest request) {
        WorkTime workTime = workTimeMapper.toWorkTime(request);
        workTimeRepository.save(workTime);
        return workTimeMapper.toWorkTimeRespon(workTime);
    }

    @Override
    public WorkTimeRespon getWorkTime(Integer id) {
        return workTimeMapper.toWorkTimeRespon(
                workTimeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    @Override
    public List<WorkTimeRespon> findAllWorkTime() {
        List<WorkTimeRespon> workTimeRespons = new ArrayList<>();
        List<WorkTime> workTimes = workTimeRepository.findAll();
        workTimes.forEach(workTime -> workTimeRespons.add(workTimeMapper.toWorkTimeRespon(workTime)));

        return workTimeRespons;
    }

    @Override
    public WorkTimeRespon updateWorkTime(Integer idWorkTime, WorkTimeRequest request) {
        WorkTime workTime =
                workTimeRepository.findById(idWorkTime).orElseThrow(() -> new RuntimeException("WorkTime not found"));
        workTimeMapper.updateWorkTime(workTime, request);
        return workTimeMapper.toWorkTimeRespon(workTimeRepository.save(workTime));
    }

    @Override
    public void deleteWorkTime(Integer idWorkTime) {
        workTimeRepository.deleteById(idWorkTime);
    }
}
