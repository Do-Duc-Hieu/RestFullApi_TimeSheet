package com.example.devTimesheet.service;


import com.example.devTimesheet.dto.request.LogOutRequest;

public interface LogOutService {
    public void logOut(LogOutRequest token);
}
