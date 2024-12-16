package com.example.devTimesheet.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.example.devTimesheet.dto.request.TimeSheetRequest;
import com.example.devTimesheet.dto.respon.TimeSheetRespon;

public interface TimeSheetService {

    TimeSheetRespon createTimeSheet(TimeSheetRequest request);

    TimeSheetRespon getTimeSheet(Integer id);

    List<TimeSheetRespon> getTimeSheetByUserAndDate(LocalDate date);

    List<TimeSheetRespon> getTimeSheetByUserAndDateRange(LocalDate startDate, LocalDate endDate);

    List<TimeSheetRespon> findAllTimeSheet();

    List<TimeSheetRespon> searchTimeSheet(
            String projectName,
            String userName,
            String branchName,
            String statusName,
            String type,
            LocalDate startDate,
            LocalDate endDate);

    TimeSheetRespon updateTimeSheet(Integer idTimeSheet, TimeSheetRequest request);

    void deleteTimeSheet(Integer idTimeSheet);

    public TimeSheetRespon browseTimeSheet(Integer idTimeSheet, TimeSheetRequest request);

    public void exportTimeSheetsToExcel(
            String projectName,
            String userName,
            String branchName,
            String statusName,
            String type,
            LocalDate startDate,
            LocalDate endDate)
            throws IOException;
}
