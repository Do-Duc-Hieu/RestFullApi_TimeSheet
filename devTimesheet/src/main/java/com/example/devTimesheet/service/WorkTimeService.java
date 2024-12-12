package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.WorkTimeRequest;
import com.example.devTimesheet.dto.respon.WorkTimeRespon;

public interface WorkTimeService {

    WorkTimeRespon createWorkTime(WorkTimeRequest timeRequest);

    WorkTimeRespon getWorkTime(Integer id);

    List<WorkTimeRespon> findAllWorkTime();

    WorkTimeRespon updateWorkTime(Integer idWorkTime, WorkTimeRequest request);

    void deleteWorkTime(Integer idWorkTime);
}
