package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.TeamRequest;
import com.example.devTimesheet.dto.request.WorkTimeRequest;
import com.example.devTimesheet.dto.respon.TeamRespon;
import com.example.devTimesheet.dto.respon.WorkTimeRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface WorkTimeService {

    WorkTimeRespon createWorkTime(WorkTimeRequest timeRequest);

    WorkTimeRespon getWorkTime(Integer id);

    List<WorkTimeRespon> findAllWorkTime();

    WorkTimeRespon updateWorkTime(Integer idWorkTime, WorkTimeRequest request);

    void deleteWorkTime(Integer idWorkTime);
}
