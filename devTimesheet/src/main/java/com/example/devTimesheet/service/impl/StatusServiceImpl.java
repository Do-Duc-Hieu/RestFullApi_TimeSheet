package com.example.devTimesheet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.StatusRequest;
import com.example.devTimesheet.dto.respon.StatusRespon;
import com.example.devTimesheet.entity.Status;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.StatusMapper;
import com.example.devTimesheet.repository.StatusRepository;
import com.example.devTimesheet.service.StatusService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatusServiceImpl implements StatusService {
    StatusRepository statusRepository;
    StatusMapper statusMapper;

    @Override
    public StatusRespon CreateStatus(StatusRequest request) {
        Status status = statusMapper.toStatus(request);
        statusRepository.save(status);
        return statusMapper.toStatusRespon(status);
    }

    @Override
    public StatusRespon getStatus(Integer id) {
        return statusMapper.toStatusRespon(
                statusRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public List<StatusRespon> findAllStatus() {
        List<StatusRespon> statusRespons = new ArrayList<>();
        List<Status> statuses = statusRepository.findAll();
        statuses.forEach(status -> statusRespons.add(statusMapper.toStatusRespon(status)));

        return statusRespons;
    }

    @Override
    public StatusRespon updateStatus(Integer idStatus, StatusRequest request) {
        Status status = statusRepository.findById(idStatus).orElseThrow(() -> new RuntimeException("Status not found"));
        statusMapper.updateStatus(status, request);
        return statusMapper.toStatusRespon(statusRepository.save(status));
    }

    @Override
    public void deleteStatus(Integer idStatus) {
        statusRepository.deleteById(idStatus);
    }
}
