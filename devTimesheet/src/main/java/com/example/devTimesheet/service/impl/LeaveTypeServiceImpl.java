package com.example.devTimesheet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.LeaveTypeRequest;
import com.example.devTimesheet.dto.respon.LeaveTypeRespon;
import com.example.devTimesheet.entity.LeaveType;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.LeaveTypeMapper;
import com.example.devTimesheet.repository.LeaveTypeRepository;
import com.example.devTimesheet.service.LeaveTypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LeaveTypeServiceImpl implements LeaveTypeService {
    LeaveTypeRepository leaveTypeRepository;
    LeaveTypeMapper leaveTypeMapper;

    @Override
    public LeaveTypeRespon createLeaveType(LeaveTypeRequest request) {
        if (leaveTypeRepository.existsByNameType(request.getNameType())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        LeaveType leaveType = leaveTypeMapper.toLeaveType(request);
        leaveTypeRepository.save(leaveType);
        return leaveTypeMapper.toLeaveTypeRespon(leaveType);
    }

    @Override
    public LeaveTypeRespon getLeaveType(Integer id) {
        return leaveTypeMapper.toLeaveTypeRespon(
                leaveTypeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public List<LeaveTypeRespon> findAllLeaveType() {
        List<LeaveTypeRespon> leaveTypeRespons = new ArrayList<>();
        List<LeaveType> leaveTypes = leaveTypeRepository.findAll();
        leaveTypes.forEach(leaveType -> leaveTypeRespons.add(leaveTypeMapper.toLeaveTypeRespon(leaveType)));

        return leaveTypeRespons;
    }

    @Override
    public LeaveTypeRespon updateLeaveType(Integer idLeaveType, LeaveTypeRequest request) {
        LeaveType leaveType = leaveTypeRepository
                .findById(idLeaveType)
                .orElseThrow(() -> new RuntimeException("LeaveType not found"));
        leaveTypeMapper.updateLeaveType(leaveType, request);
        return leaveTypeMapper.toLeaveTypeRespon(leaveTypeRepository.save(leaveType));
    }

    @Override
    public void deleteLeaveType(Integer idLeaveType) {
        leaveTypeRepository.deleteById(idLeaveType);
    }
}
