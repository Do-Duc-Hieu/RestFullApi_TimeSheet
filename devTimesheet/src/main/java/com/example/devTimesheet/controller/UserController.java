package com.example.devTimesheet.controller;


import com.example.devTimesheet.dto.request.RequestRequest;
import com.example.devTimesheet.dto.request.TimeSheetRequest;
import com.example.devTimesheet.dto.request.WorkTimeRequest;
import com.example.devTimesheet.dto.respon.*;
import com.example.devTimesheet.service.EmailService;
import com.example.devTimesheet.service.RequestService;
import com.example.devTimesheet.service.TimeSheetService;
import com.example.devTimesheet.service.WorkTimeService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    TimeSheetService timeSheetService;
    WorkTimeService workTimeService;
    RequestService requestService;
    EmailService emailService;

    //Load image
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/avatar/{filename}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get("C:\\devTimesheet\\devTimesheet\\src\\uploads", filename);
        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }


    // Service TimeSheet
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getTimeSheetByUserAndDate/{date}")
    public ApiRespon<List<TimeSheetRespon>> findTimeSheetByUserAndDate(@PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        return ApiRespon.<List<TimeSheetRespon>>builder()
                .result(timeSheetService.getTimeSheetByUserAndDate(localDate)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getTimeSheetByUserAndDateRange/{startDate}/{endDate}")
    public ApiRespon<List<TimeSheetRespon>> findTimeSheetByUserAndDateRange
            (@PathVariable String startDate,
             @PathVariable String endDate) {

        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        return ApiRespon.<List<TimeSheetRespon>>builder()
                .result(timeSheetService.getTimeSheetByUserAndDateRange(
                        startLocalDate, endLocalDate))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/searchTimeSheet")
    public ApiRespon<List<TimeSheetRespon>> searchTimeSheet(
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String branchName,
            @RequestParam(required = false) String statusName,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ApiRespon.<List<TimeSheetRespon>>builder()
                .result(timeSheetService
                        .searchTimeSheet(projectName, userName,
                                branchName, statusName, type, startDate, endDate))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getAllTimeSheet")
    public ApiRespon<List<TimeSheetRespon>> findAllTimeSheet() {

        return ApiRespon.<List<TimeSheetRespon>>builder()
                .result(timeSheetService.findAllTimeSheet()).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getTimeSheet/{idTimeSheet}")
    public ApiRespon<TimeSheetRespon> getTimeSheet(@PathVariable Integer idTimeSheet) {

        return ApiRespon.<TimeSheetRespon>builder()
                .result(timeSheetService.getTimeSheet(idTimeSheet)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PostMapping("/addTimeSheet")
    public ApiRespon<TimeSheetRespon> addTImeSheet(@RequestBody @Valid TimeSheetRequest request) {

        return ApiRespon.<TimeSheetRespon>builder()
                .result(timeSheetService.createTimeSheet(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PutMapping("/updateTimeSheet/{idTimeSheet}")
    public ApiRespon<TimeSheetRespon> updateTimeSheet(@PathVariable Integer idTimeSheet,
                                                      @RequestBody @Valid TimeSheetRequest request){
        return ApiRespon.<TimeSheetRespon>builder()
                .result(timeSheetService.updateTimeSheet(idTimeSheet, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @DeleteMapping("/deleteTimeSheet/{idTimeSheet}")
    public ApiRespon<String> deleteTimeSheet(@PathVariable Integer idTimeSheet){

        timeSheetService.deleteTimeSheet(idTimeSheet);
        return ApiRespon.<String>builder()
                .result("TimeSheet has been deleted").build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/browseTimeSheet/{idTimeSheet}")
    public ApiRespon<TimeSheetRespon> browseTimeSheet(@PathVariable Integer idTimeSheet,
                                                      @RequestBody @Valid TimeSheetRequest request){

        return ApiRespon.<TimeSheetRespon>builder()
                .result(timeSheetService.browseTimeSheet(idTimeSheet, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/exportTimeSheet")
    public ApiRespon<String> exportTimeSheetsToExcel(
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String branchName,
            @RequestParam(required = false) String statusName,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
            throws IOException {

        timeSheetService.exportTimeSheetsToExcel(
                projectName, userName, branchName, statusName, type, startDate, endDate);

        return ApiRespon.<String>builder()
                .result("TimeSheet has been export").build();
    }


    //Service worktime
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PostMapping("/addWorkTime")
    public ApiRespon<WorkTimeRespon> addWorkTime(@RequestBody @Valid WorkTimeRequest request) {

        return ApiRespon.<WorkTimeRespon>builder()
                .result(workTimeService.createWorkTime(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getAllWorkTime")
    public ApiRespon<List<WorkTimeRespon>> findAllWorkTime() {

        return ApiRespon.<List<WorkTimeRespon>>builder()
                .result(workTimeService.findAllWorkTime()).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getWorkTime/{idWorkTime}")
    public ApiRespon<WorkTimeRespon> getWorkTime(@PathVariable Integer idWorkTime) {

        return ApiRespon.<WorkTimeRespon>builder()
                .result(workTimeService.getWorkTime(idWorkTime)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PutMapping("/updateWorkTime/{idWorkTime}")
    public ApiRespon<WorkTimeRespon> updateWorkTime(@PathVariable Integer idWorkTime,
                                                    @RequestBody @Valid WorkTimeRequest request){

        return ApiRespon.<WorkTimeRespon>builder()
                .result(workTimeService.updateWorkTime(idWorkTime, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @DeleteMapping("/deleteWorkTime/{idWorkTime}")
    public ApiRespon<String> deleteWorkTime(@PathVariable Integer idWorkTime){

        workTimeService.deleteWorkTime(idWorkTime);
        return ApiRespon.<String>builder()
                .result("WorkTime has been deleted").build();
    }


    //Service request
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PostMapping("/addRequest")
    public ApiRespon<RequestRespon> addRequest(@RequestBody @Valid RequestRequest request) {

        return ApiRespon.<RequestRespon>builder()
                .result(requestService.createRequest(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllRequest")
    public ApiRespon<List<RequestRespon>> findAllRequest() {

        return ApiRespon.<List<RequestRespon>>builder()
                .result(requestService.findAllRequest()).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getRequest/{idRequest}")
    public ApiRespon<RequestRespon> getRequest(@PathVariable Integer idRequest) {

        return ApiRespon.<RequestRespon>builder()
                .result(requestService.getRequest(idRequest)).build();
    }

    @GetMapping("/getRequestByUserAndDateRangeExcludingWorkTime/{startDate}/{endDate}")
    public ApiRespon<List<RequestRespon>> findRequestByUserAndDateRangeLeave
            (@PathVariable String startDate,
             @PathVariable String endDate) {

        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        return ApiRespon.<List<RequestRespon>>builder()
                .result(requestService.getRequestByUserAndDateRangeExcludingWorkTime
                        (startLocalDate, endLocalDate))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/getWorkTimeRequestsByUserAndDateRange/{startDate}/{endDate}")
    public ApiRespon<List<RequestRespon>> findWorkTimeRequestsByUserAndDateRange
            (@PathVariable String startDate,
             @PathVariable String endDate) {

        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        return ApiRespon.<List<RequestRespon>>builder()
                .result(requestService
                        .getWorkTimeRequestsByUserAndDateRange(startLocalDate, endLocalDate))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getWorkTimeRequestsByUserAndPending/{nameStatus}")
    public ApiRespon<List<RequestRespon>> findAllWorkTimeRequestsByUserAndPending
            (@PathVariable String nameStatus,
             @RequestParam List<String> nameProjects) {

        return ApiRespon.<List<RequestRespon>>builder()
                .result(requestService
                        .findAllWorkTimeRequestsByUserAndPending(nameStatus, nameProjects))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/searchRequestByProject")
    public ApiRespon<List<RequestRespon>> searchRequestByProject(
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String statusName,
            @RequestParam(required = false) String nameRequestType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ApiRespon.<List<RequestRespon>>builder()
                .result(requestService
                        .searchRequestByProject(projectName, email, statusName, nameRequestType, startDate, endDate))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/searchRequestByBranch")
    public ApiRespon<List<RequestRespon>> searchRequestByBranch(
            @RequestParam(required = false) String branchName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String statusName,
            @RequestParam(required = false) String nameRequestType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ApiRespon.<List<RequestRespon>>builder()
                .result(requestService
                        .searchRequestByBranch(branchName, email, statusName, nameRequestType, startDate, endDate))
                .build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PutMapping("/updateRequest/{idRequest}")
    public ApiRespon<RequestRespon> updateRequest(
            @PathVariable Integer idRequest ,
            @RequestBody @Valid RequestRequest request){

        return ApiRespon.<RequestRespon>builder()
                .result(requestService.updateRequest(idRequest, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @PutMapping("/browseRequest/{idRequest}")
    public ApiRespon<RequestRespon> browseRequest(
            @PathVariable Integer idRequest ,
            @RequestBody @Valid RequestRequest request){

        return ApiRespon.<RequestRespon>builder()
                .result(requestService.browseRequest(idRequest, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @DeleteMapping("/deleteRequest/{idRequest}")
    public ApiRespon<String> deleteRequest(@PathVariable Integer idRequest){

        requestService.deleteRequest(idRequest);
        return ApiRespon.<String>builder()
                .result("Request has been deleted").build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/exportRequest")
    public ApiRespon<String> exportRequestToExcel(
            @RequestParam(required = false) String projectName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String statusName,
            @RequestParam(required = false) String nameRequestType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
            throws IOException {

        requestService.exportRequestToExcel(
                projectName, email, statusName, nameRequestType, startDate, endDate);

        return ApiRespon.<String>builder()
                .result("Request has been export").build();
    }

    //Service email
    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/sendEmail")
    public ApiRespon<String> exportRequestToExcel(@RequestParam String userName,
                                                @RequestParam String newPassword)
            throws IOException, MessagingException {

        emailService.sendEmail(userName, newPassword);

        return ApiRespon.<String>builder()
                .result("Email has been send").build();
    }
}
