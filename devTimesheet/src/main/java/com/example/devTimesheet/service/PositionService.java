package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.PositionRequest;
import com.example.devTimesheet.dto.respon.PositionRespon;

public interface PositionService {

    PositionRespon createPosition(PositionRequest positionRequest);

    PositionRespon getPosition(Integer id);

    List<PositionRespon> findAllPosition();

    PositionRespon updatePosition(Integer idPosition, PositionRequest request);

    void deletePosition(Integer idPosition);
}
