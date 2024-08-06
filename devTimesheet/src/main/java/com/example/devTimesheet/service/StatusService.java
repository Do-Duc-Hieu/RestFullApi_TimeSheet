package com.example.devTimesheet.service;


import com.example.devTimesheet.dto.request.StatusRequest;
import com.example.devTimesheet.dto.respon.StatusRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface StatusService {

    StatusRespon CreateStatus(StatusRequest statusRequest);

    StatusRespon getStatus(Integer id);

    List<StatusRespon> findAllStatus();

    StatusRespon updateStatus(Integer idStatus, StatusRequest request);

    void deleteStatus(Integer idStatus);
}
