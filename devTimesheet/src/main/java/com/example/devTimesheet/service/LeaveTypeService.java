package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.LeaveTypeRequest;
import com.example.devTimesheet.dto.respon.LeaveTypeRespon;

public interface LeaveTypeService {

    LeaveTypeRespon createLeaveType(LeaveTypeRequest leaveTypeRequest);

    LeaveTypeRespon getLeaveType(Integer id);

    List<LeaveTypeRespon> findAllLeaveType();

    LeaveTypeRespon updateLeaveType(Integer idLeaveType, LeaveTypeRequest request);

    void deleteLeaveType(Integer idLeaveType);
}
