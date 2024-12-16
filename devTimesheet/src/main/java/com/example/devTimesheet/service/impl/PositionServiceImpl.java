package com.example.devTimesheet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.PositionRequest;
import com.example.devTimesheet.dto.respon.PositionRespon;
import com.example.devTimesheet.entity.Position;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.PositionMapper;
import com.example.devTimesheet.repository.PositionRepository;
import com.example.devTimesheet.service.PositionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PositionServiceImpl implements PositionService {
    PositionRepository positionRepository;
    PositionMapper positionMapper;

    @Override
    public PositionRespon createPosition(PositionRequest request) {
        Position position = positionMapper.toPosition(request);
        positionRepository.save(position);
        return positionMapper.toPositionRespon(position);
    }

    @Override
    public PositionRespon getPosition(Integer id) {
        return positionMapper.toPositionRespon(
                positionRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public List<PositionRespon> findAllPosition() {
        List<PositionRespon> positionRespons = new ArrayList<>();
        List<Position> positions = positionRepository.findAll();
        positions.forEach(position -> positionRespons.add(positionMapper.toPositionRespon(position)));

        return positionRespons;
    }

    @Override
    public PositionRespon updatePosition(Integer idPosition, PositionRequest request) {
        Position position =
                positionRepository.findById(idPosition).orElseThrow(() -> new RuntimeException("Position not found"));
        positionMapper.updatePosition(position, request);
        return positionMapper.toPositionRespon(positionRepository.save(position));
    }

    @Override
    public void deletePosition(Integer idPosition) {
        positionRepository.deleteById(idPosition);
    }
}
