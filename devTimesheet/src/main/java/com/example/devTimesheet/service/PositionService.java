package com.example.devTimesheet.service;


import com.example.devTimesheet.dto.request.PositionRequest;
import com.example.devTimesheet.dto.respon.PositionRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PositionService {

    PositionRespon createPosition(PositionRequest positionRequest);

    PositionRespon getPosition(Integer id);

    List<PositionRespon> findAllPosition();

    PositionRespon updatePosition(Integer idPosition, PositionRequest request);

    void deletePosition(Integer idPosition);
}
