package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.dto.request.RequestRequest;
import com.example.devTimesheet.dto.request.TimeSheetRequest;
import com.example.devTimesheet.dto.respon.RequestRespon;
import com.example.devTimesheet.dto.respon.TimeSheetRespon;
import com.example.devTimesheet.entity.*;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.RequestMapper;
import com.example.devTimesheet.repository.RequestRepository;
import com.example.devTimesheet.repository.RequestTypeRepository;
import com.example.devTimesheet.repository.StatusRepository;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.service.RequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestServiceImpl implements RequestService {
    RequestRepository requestRepository;
    RequestTypeRepository requestTypeRepository;
    StatusRepository statusRepository;
    UserRepository userRepository;
    RequestMapper requestMapper;

    @Override
    public RequestRespon createRequest(RequestRequest requestRequest){

        Request request = requestMapper.toRequest(requestRequest);
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        request.setCreatedAt(LocalDate.now());
        request.setUser((User) userRepository.findUserByUsername(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        request.setStatus((Status) statusRepository.findById(1)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        RequestType requestType = requestTypeRepository.save(request.getRequestType());
        request.setRequestType(requestType);
        request = requestRepository.save(request);
        log.info(request.getRequestType().getClass().getName());
        return requestMapper.toRequestRespon(request);
    }

    @Override
    public RequestRespon getRequest (Integer id){
        Request request = requestRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        log.info(request.getRequestType().getClass().getName());
        return requestMapper.toRequestRespon(request);
    }

    @Override
    public List<RequestRespon> getRequestByUserAndDateRangeExcludingWorkTime (
            LocalDate startDate, LocalDate endDate){

        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        List<RequestRespon> requestRespons = new ArrayList<>();
        List<Request> requests = requestRepository
                .findByUsernameAndDateRangeExcludingWorkTime(userName, startDate, endDate);
        requests.forEach(
                request -> requestRespons.add(requestMapper.toRequestRespon(request))

        );
        return requestRespons;
    }

    @Override
    public List<RequestRespon> getWorkTimeRequestsByUserAndDateRange(
            LocalDate startDate, LocalDate endDate) {

        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        List<RequestRespon> requestRespons = new ArrayList<>();
        List<Request> requests = requestRepository
                .findAllWorkTimeRequestsByUserAndDateRange(userName, startDate, endDate);
        requests.forEach(
                request -> requestRespons.add(requestMapper.toRequestRespon(request))

        );
        return requestRespons;
    }

    @Override
    public List<RequestRespon> findAllWorkTimeRequestsByUserAndPending(
            String statusName, List<String> nameProjects) {

        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        List<RequestRespon> requestRespons = new ArrayList<>();
        List<Request> requests = requestRepository.
                findAllWorkTimeRequestsByUserAndPending(userName, statusName, nameProjects);
        requests.forEach(
                request -> requestRespons.add(requestMapper.toRequestRespon(request))

        );
        return requestRespons;
    }

    @Override
    public List<RequestRespon> findAllRequest(){
        List<RequestRespon> requestRespons = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        requests.forEach(
                request -> requestRespons.add(requestMapper.toRequestRespon(request))
        );
        return requestRespons;
    }

    @Override
    public List<RequestRespon> searchRequestByProject(
            String projectName, String email,
            String statusName, String nameRequestType,
            LocalDate startDate, LocalDate endDate){

        List<RequestRespon> requestRespons = new ArrayList<>();
        Class<? extends RequestType> requestTypeClass;
        if(nameRequestType != null){
            switch (nameRequestType.toUpperCase()) {
                case "LAST":
                    requestTypeClass = RequestLast.class;
                    break;
                case "OFF":
                    requestTypeClass = RequestOff.class;
                    break;
                case "REMOTE":
                    requestTypeClass = RequestRemote.class;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown request type: " + nameRequestType);
            }
        }else{
            requestTypeClass = null;
        }
        List<Request> requests = requestRepository.searchRequestsByProject(
                projectName, statusName, email, requestTypeClass, startDate, endDate);
        requests.forEach(
                request -> requestRespons.add(requestMapper.toRequestRespon(request))

        );
        return requestRespons;
    }

    @Override
    public List<RequestRespon> searchRequestByBranch(
            String branchName, String email,
            String statusName, String nameRequestType,
            LocalDate startDate, LocalDate endDate){

        List<RequestRespon> requestRespons = new ArrayList<>();
        Class<? extends RequestType> requestTypeClass;
        if(nameRequestType != null){
            switch (nameRequestType.toUpperCase()) {
                case "LAST":
                    requestTypeClass = RequestLast.class;
                    break;
                case "OFF":
                    requestTypeClass = RequestOff.class;
                    break;
                case "REMOTE":
                    requestTypeClass = RequestRemote.class;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown request type: " + nameRequestType);
            }
        }else{
            requestTypeClass = null;
        }
        List<Request> requests = requestRepository.searchRequestsByBranch(
                branchName, statusName, email, requestTypeClass, startDate, endDate);
        requests.forEach(
                request -> requestRespons.add(requestMapper.toRequestRespon(request))

        );
        return requestRespons;
    }

    @Override
    public RequestRespon updateRequest(Integer idRequest, RequestRequest requestRequest){

        Request request = requestRepository.findById(idRequest)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        requestMapper.updateRequest(request, requestRequest);
        request.setLastModifiedAt(LocalDate.now());
        RequestType requestType = requestTypeRepository.save(request.getRequestType());
        request.setRequestType(requestType);
        request = requestRepository.save(request);
        log.info(requestMapper.toRequestRespon(request).getRequestTypeRespon().toString());
        return requestMapper.toRequestRespon(request);
    }

    @Override
    public void deleteRequest(Integer idRequest){
        requestRepository.deleteById(idRequest);
    }

    @Override
    public RequestRespon browseRequest(Integer idRequest, RequestRequest requestRequest){

        Request request = requestRepository.findById(idRequest)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        requestMapper.updateRequest(request, requestRequest);
        RequestType requestType = requestTypeRepository.save(request.getRequestType());
        request.setRequestType(requestType);
        if(request.getRequestType() instanceof RequestWorkTime){
            if(requestRequest.getIdStatus() == 2){
                String nameStatus ="Approved";
                Status status = statusRepository.findById(4)
                        .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
                List<Request> oldRequests = requestRepository
                        .findAllWorkTimeRequestByUserAndStatusName(request.getUser().getUsername(), nameStatus);
                oldRequests.forEach(oldRequest->{
                    oldRequest.setStatus(status);
                    requestRepository.save(oldRequest);
                });
            }
        }
        request.setStatus((Status) statusRepository.findById(requestRequest.getIdStatus())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        request = requestRepository.save(request);
        log.info(requestMapper.toRequestRespon(request).getRequestTypeRespon().toString());
        return requestMapper.toRequestRespon(request);
    }

    public void exportRequestToExcel(
            String projectName, String email,
            String statusName, String nameRequestType,
            LocalDate startDate, LocalDate endDate)
            throws IOException {

        List<RequestRespon> requestRespons = searchRequestByProject(
                projectName, statusName, email, nameRequestType, startDate, endDate);
        String filePath = "C:\\rac\\devTimesheet\\devTimesheet\\src\\main\\resources\\static\\Requests.xlsx";
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("Requests");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Create at", "Date", "Last modified at", "Request type", "Status", "User"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Create data rows
            int rowIdx = 1;
            for (RequestRespon requestRespon : requestRespons) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(requestRespon.getId());
                row.createCell(1).setCellValue(requestRespon.getCreatedAt());
                row.createCell(2).setCellValue(requestRespon.getDate());
                row.createCell(3).setCellValue(requestRespon.getLastModifiedAt());
                row.createCell(4).setCellValue(requestRespon.getRequestTypeRespon().getType());
                row.createCell(5).setCellValue(requestRespon.getStatusRespon().getNameStatus());
                row.createCell(6).setCellValue(requestRespon.getUserRespon().getUsername());
            }

            workbook.write(fileOut);
        }
    }
}