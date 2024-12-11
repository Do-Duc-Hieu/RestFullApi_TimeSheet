package com.example.devTimesheet.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.TimeSheetRequest;
import com.example.devTimesheet.dto.respon.TimeSheetRespon;
import com.example.devTimesheet.entity.*;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.TimeSheetMapper;
import com.example.devTimesheet.repository.ProjectRepository;
import com.example.devTimesheet.repository.StatusRepository;
import com.example.devTimesheet.repository.TimeSheetRepository;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.service.TimeSheetService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeSheetServiceImpl implements TimeSheetService {

    TimeSheetRepository timeSheetRepository;
    UserRepository userRepository;
    ProjectRepository projectRepository;
    StatusRepository statusRepository;
    TimeSheetMapper timeSheetMapper;

    @Override
    public TimeSheetRespon createTimeSheet(TimeSheetRequest request) {
        TimeSheet timeSheet = timeSheetMapper.toTimeSheet(request);
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        timeSheet.setUser((User) userRepository
                .findUserByUsername(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
        timeSheet.setProject((Project) projectRepository
                .findProjectByNameProject(request.getNameProject())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
        timeSheet.setStatus(
                (Status) statusRepository.findById(4).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));

        timeSheetRepository.save(timeSheet);

        return timeSheetMapper.toTimeSheetRespon(timeSheet);
    }

    @Override
    public TimeSheetRespon getTimeSheet(Integer id) {
        return timeSheetMapper.toTimeSheetRespon(
                timeSheetRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public List<TimeSheetRespon> getTimeSheetByUserAndDate(LocalDate date) {
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        List<TimeSheetRespon> timeSheetRespons = new ArrayList<>();
        List<TimeSheet> timeSheets = timeSheetRepository.findByUsernameAndDate(userName, date);
        timeSheets.forEach(timeSheet -> timeSheetRespons.add(timeSheetMapper.toTimeSheetRespon(timeSheet)));

        return timeSheetRespons;
    }

    @Override
    public List<TimeSheetRespon> getTimeSheetByUserAndDateRange(LocalDate startDate, LocalDate endDate) {
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        List<TimeSheetRespon> timeSheetRespons = new ArrayList<>();
        List<TimeSheet> timeSheets = timeSheetRepository.findByUsernameAndDateRange(userName, startDate, endDate);
        timeSheets.forEach(timeSheet -> timeSheetRespons.add(timeSheetMapper.toTimeSheetRespon(timeSheet)));

        return timeSheetRespons;
    }

    @Override
    public List<TimeSheetRespon> findAllTimeSheet() {
        List<TimeSheetRespon> timeSheetRespons = new ArrayList<>();
        List<TimeSheet> timeSheets = timeSheetRepository.findAll();
        timeSheets.forEach(timeSheet -> timeSheetRespons.add(timeSheetMapper.toTimeSheetRespon(timeSheet)));

        return timeSheetRespons;
    }

    @Override
    public List<TimeSheetRespon> searchTimeSheet(
            String projectName,
            String userName,
            String branchName,
            String statusName,
            String type,
            LocalDate startDate,
            LocalDate endDate) {
        List<TimeSheetRespon> timeSheetRespons = new ArrayList<>();
        List<TimeSheet> timeSheets = timeSheetRepository.searchTimeSheets(
                projectName, userName, branchName, statusName, type, startDate, endDate);
        timeSheets.forEach(timeSheet -> timeSheetRespons.add(timeSheetMapper.toTimeSheetRespon(timeSheet)));

        return timeSheetRespons;
    }

    @Override
    public TimeSheetRespon updateTimeSheet(Integer idTimeSheet, TimeSheetRequest request) {
        TimeSheet timeSheet = timeSheetRepository
                .findById(idTimeSheet)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        timeSheet.setProject((Project) projectRepository
                .findProjectByNameProject(request.getNameProject())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
        timeSheetMapper.updateTimeSheet(timeSheet, request);
        return timeSheetMapper.toTimeSheetRespon(timeSheetRepository.save(timeSheet));
    }

    @Override
    public void deleteTimeSheet(Integer idTimeSheet) {
        timeSheetRepository.deleteById(idTimeSheet);
    }

    @Override
    public TimeSheetRespon browseTimeSheet(Integer idTimeSheet, TimeSheetRequest request) {
        TimeSheet timeSheet = timeSheetRepository
                .findById(idTimeSheet)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        timeSheet.setStatus((Status) statusRepository
                .findById(request.getIdStatus())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
        timeSheetMapper.updateTimeSheet(timeSheet, request);
        return timeSheetMapper.toTimeSheetRespon(timeSheetRepository.save(timeSheet));
    }

    public void exportTimeSheetsToExcel(
            String projectName,
            String userName,
            String branchName,
            String statusName,
            String type,
            LocalDate startDate,
            LocalDate endDate)
            throws IOException {

        List<TimeSheetRespon> timeSheets =
                searchTimeSheet(projectName, userName, branchName, statusName, type, startDate, endDate);
        String filePath = "C:\\rac\\devTimesheet\\devTimesheet\\src\\main\\resources\\static\\TimeSheets.xlsx";
        try (Workbook workbook = new XSSFWorkbook();
                FileOutputStream fileOut = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("TimeSheets");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Project Name", "User Name", "Branch Name", "Status Name", "Type", "Date"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            int rowIdx = 1;
            for (TimeSheetRespon timeSheet : timeSheets) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(timeSheet.getId());
                row.createCell(1).setCellValue(timeSheet.getNote());
                row.createCell(2).setCellValue(timeSheet.getDate());
                row.createCell(3).setCellValue(timeSheet.getWorkingTime());
                row.createCell(4).setCellValue(timeSheet.getType());
                row.createCell(5).setCellValue(timeSheet.getTask());
                row.createCell(6).setCellValue(timeSheet.getStatusRespon().getNameStatus());
                row.createCell(7).setCellValue(timeSheet.getUserRespon().getUsername());
                row.createCell(8).setCellValue(timeSheet.getProjectRespon().getNameProject());
            }

            workbook.write(fileOut);
        }
    }
}
