package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.StatusRequest;
import com.example.devTimesheet.dto.respon.StatusRespon;

public interface StatusService {

    StatusRespon CreateStatus(StatusRequest statusRequest);

    StatusRespon getStatus(Integer id);

    List<StatusRespon> findAllStatus();

    StatusRespon updateStatus(Integer idStatus, StatusRequest request);

    void deleteStatus(Integer idStatus);
}
