package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.LeaveTypeRequest;
import com.example.devTimesheet.dto.respon.LeaveTypeRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface LeaveTypeService {

    LeaveTypeRespon createLeaveType(LeaveTypeRequest leaveTypeRequest);

    LeaveTypeRespon getLeaveType(Integer id);

    List<LeaveTypeRespon> findAllLeaveType();

    LeaveTypeRespon updateLeaveType(Integer idLeaveType, LeaveTypeRequest request);

    void deleteLeaveType(Integer idLeaveType);
}
