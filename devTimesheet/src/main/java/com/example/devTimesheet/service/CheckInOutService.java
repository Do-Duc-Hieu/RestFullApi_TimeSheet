package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.respon.CheckInOutRespon;

public interface CheckInOutService {
    CheckInOutRespon createCheckInOut(String request);
}
