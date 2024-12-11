package com.example.devTimesheet.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.LogOutRequest;
import com.example.devTimesheet.entity.InvalidatedToken;
import com.example.devTimesheet.repository.InvalidatedTokenRepository;
import com.example.devTimesheet.service.LogOutService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogOutServiceImpl implements LogOutService {
    InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public void logOut(LogOutRequest request) {
        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(request.getToken())
                .expiryTime(new Date())
                .build();
        invalidatedTokenRepository.save(invalidatedToken);
    }
}
