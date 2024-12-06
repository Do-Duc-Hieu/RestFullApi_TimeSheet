package com.example.devTimesheet.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.example.devTimesheet.dto.request.RequestRequest;
import com.example.devTimesheet.dto.respon.RequestRespon;

public interface RequestService {

    RequestRespon createRequest(RequestRequest requestRequest);

    RequestRespon getRequest(Integer id);

    List<RequestRespon> findAllRequest();

    List<RequestRespon> getRequestByUserAndDateRangeExcludingWorkTime(LocalDate startDate, LocalDate endDate);

    List<RequestRespon> getWorkTimeRequestsByUserAndDateRange(LocalDate startDate, LocalDate endDate);

    List<RequestRespon> findAllWorkTimeRequestsByUserAndPending(String statusName, List<String> nameProjects);

    List<RequestRespon> searchRequestByProject(
            String projectName,
            String email,
            String statusName,
            String nameRequestType,
            LocalDate startDate,
            LocalDate endDate);

    List<RequestRespon> searchRequestByBranch(
            String branchName,
            String email,
            String statusName,
            String nameRequestType,
            LocalDate startDate,
            LocalDate endDate);

    RequestRespon updateRequest(Integer idRequest, RequestRequest request);

    void deleteRequest(Integer idRequest);

    RequestRespon browseRequest(Integer idRequest, RequestRequest request);

    public void exportRequestToExcel(
            String projectName,
            String email,
            String statusName,
            String nameRequestType,
            LocalDate startDate,
            LocalDate endDate)
            throws IOException;
}
