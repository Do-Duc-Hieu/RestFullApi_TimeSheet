package com.example.devTimesheet.controller;


import com.example.devTimesheet.dto.request.*;
import com.example.devTimesheet.dto.respon.*;
import com.example.devTimesheet.projection.UserProjection;
import com.example.devTimesheet.service.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminController {

    UserService userService;
    RoleService roleService;
    ClientService clientService;
    TaskService taskService;
    ProjectService projectService;
    TeamService teamService;
    PositionService positionService;
    StatusService statusService;
    PunishmentService punishmentService;
    LeaveTypeService leaveTypeService;
    SalaryService salaryService;


    @GetMapping("/myInfo")
    public ApiRespon<UserRespon> getMyInfo(){
        return ApiRespon.<UserRespon>builder()
                .result(userService.getMyInfo())
                .build();
    }


    //Service user
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllUser")
    public ApiRespon<List<UserRespon>> findAllUser() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiRespon.<List<UserRespon>>builder()
                .result(userService.findAllUser()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/searchUser")
    public ApiRespon<List<UserRespon>> searchUser(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String userType,
            @RequestParam(required = false) String role) {

        return ApiRespon.<List<UserRespon>> builder()
                .result(userService.searchUser(username, branch, userType, role)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/searchUserProjection")
    public ApiRespon<List<UserProjection>> searchUserProjecttion(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String branch,
            @RequestParam(required = false) String userType,
            @RequestParam(required = false) String role) {

        return ApiRespon.<List<UserProjection>> builder()
                .result(userService.searchUserProjection(username, branch, userType, role)).build();
    }

    @PostAuthorize("returnObject.result.username == authentication.name or hasAuthority('Admin')")
    @GetMapping("/getUser/{idUser}")
    public ApiRespon<UserRespon> getUser(@PathVariable Integer idUser) {

        return ApiRespon.<UserRespon>builder()
                .result(userService.getUser(idUser)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addUser")
    public ApiRespon<UserRespon> addUser(@RequestPart("request") @Valid UserRequest request,
                                         @RequestPart("avatar") MultipartFile avatar) {

        try {
            return ApiRespon.<UserRespon>builder()
                    .result(userService.createUser(request, avatar)).build();
        } catch (IOException e) {
            // Xử lý ngoại lệ, có thể log lại hoặc ném ngoại lệ tùy chỉnh
            e.printStackTrace(); // Hoặc sử dụng logger để log lỗi
            return ApiRespon.<UserRespon>builder()
                    .result(new UserRespon()).build();
        }
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateUser/{idUser}")
    public ApiRespon<UserRespon> updateUser(
            @PathVariable Integer idUser,
            @RequestBody @Valid UserUpdateRequest request){

        return ApiRespon.<UserRespon>builder()
                .result(userService.updateUser(idUser, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/resetPassword/{idUser}")
    public ApiRespon<String> resetPassword(
            @PathVariable Integer idUser,
            @RequestParam String newPassword){

        userService.resetPassword(idUser, newPassword);
        return ApiRespon.<String>builder()
                .result("Password has been reset").build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/resetPasswordByEmail/{idUser}")
    public ApiRespon<String> resetPasswordEmailByEmail(
            @PathVariable Integer idUser,
            @RequestParam String newPassword,
            @RequestParam String token){

        userService.resetPassword(idUser, newPassword);
        return ApiRespon.<String>builder()
                .result("Password has been reset").build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteUser/{idUser}")
    public ApiRespon<String> deleteUser(@PathVariable Integer idUser){

        userService.deleteUser(idUser);
        return ApiRespon.<String>builder()
                .result("User has been deleted").build();
    }



    // Service Role
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllRole")
    public ApiRespon<List<RoleRespon>> findAllRole() {

        return ApiRespon.<List<RoleRespon>>builder()
                .result(roleService.findAllRole()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getRole/{idRole}")
    public ApiRespon<RoleRespon> getRole(@PathVariable Integer idRole) {

        return ApiRespon.<RoleRespon>builder()
                .result(roleService.getRole(idRole)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addRole")
    public ApiRespon<RoleRespon> addRole(@RequestBody @Valid RoleRequest request) {

        return ApiRespon.<RoleRespon>builder()
                .result(roleService.createRole(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateRole/{idRole}")
    public ApiRespon<RoleRespon> updateRole(
            @PathVariable Integer idRole,
            @RequestBody RoleRequest request){

        return ApiRespon.<RoleRespon>builder()
                .result(roleService.updateRole(idRole, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteRole/{idRole}")
    public ApiRespon<String> deleteRole(@PathVariable Integer idRole){

        roleService.deleteRole(idRole);
        return ApiRespon.<String>builder()
                .result("Role has been deleted").build();
    }



    //Server task
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addTask")
    public ApiRespon<TaskRespon> addTask(@RequestBody @Valid TaskRequest request) {

        return ApiRespon.<TaskRespon>builder()
                .result(taskService.CreateTask(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllTask")
    public ApiRespon<List<TaskRespon>> findAllTask() {

        return ApiRespon.<List<TaskRespon>>builder()
                .result(taskService.findAllTask()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getTask/{idTask}")
    public ApiRespon<TaskRespon> getTask(@PathVariable Integer idTask) {

        return ApiRespon.<TaskRespon>builder()
                .result(taskService.getTask(idTask)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateTask/{idTask}")
    public ApiRespon<TaskRespon> updateTask(
            @PathVariable Integer idTask,
            @RequestBody @Valid TaskRequest request){

        return ApiRespon.<TaskRespon>builder()
                .result(taskService.updateTask(idTask, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteTask/{idTask}")
    public ApiRespon<String> deleteTask(@PathVariable Integer idTask){

        taskService.deleteTask(idTask);
        return ApiRespon.<String>builder()
                .result("Task has been deleted").build();
    }



    //Service client
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addClient")
    public ApiRespon<ClientRespon> addClient(@RequestBody @Valid ClientRequest request) {

        return ApiRespon.<ClientRespon>builder()
                .result(clientService.createClient(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllClient")
    public ApiRespon<List<ClientRespon>> findAllClient() {

        return ApiRespon.<List<ClientRespon>>builder()
                .result(clientService.findAllClient()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getClient/{idClient}")
    public ApiRespon<ClientRespon> getClient(@PathVariable Integer idClient) {

        return ApiRespon.<ClientRespon>builder()
                .result(clientService.getClient(idClient)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateClient/{idClient}")
    public ApiRespon<ClientRespon> updateClient(
            @PathVariable Integer idClient,
            @RequestBody @Valid ClientRequest request){

        return ApiRespon.<ClientRespon>builder()
                .result(clientService.updateClient(idClient, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteClient/{idClient}")
    public ApiRespon<String> deleteClient(@PathVariable Integer idClient){

        clientService.deleteClient(idClient);
        return ApiRespon.<String>builder()
                .result("Client has been deleted").build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllClientPage")
    public ApiRespon<Page<ClientRespon>> findAllClient(
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ApiRespon.<Page<ClientRespon>>builder()
                .result(clientService.findAllClientPage(pageable))
                .build();
    }

    //Service Project
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addProject")
    public ApiRespon<ProjectRespon> addProject(@RequestBody @Valid ProjectRequest request) {

        return ApiRespon.<ProjectRespon>builder()
                .result(projectService.createProject(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
    @GetMapping("/searchProjectByIdUser/{idUser}")
    public ApiRespon<List<ProjectRespon>> searchProjectByIdUser(@PathVariable Integer idUser) {

        return ApiRespon.<List<ProjectRespon>>builder()
                .result(projectService.searchProjectByIdUser(idUser)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllProject")
    public ApiRespon<List<ProjectRespon>> findAllProject() {

        return ApiRespon.<List<ProjectRespon>>builder()
                .result(projectService.findAllProject()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getProject/{idProject}")
    public ApiRespon<ProjectRespon> getProject(@PathVariable Integer idProject) {

        return ApiRespon.<ProjectRespon>builder()
                .result(projectService.getProject(idProject)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getUserPositionByProject/{idProject}")
    public ApiRespon<List<UserPositionRespon>> gettUserPositionByProject(@PathVariable Integer idProject) {

        return ApiRespon.<List<UserPositionRespon>>builder()
                .result(projectService.getUsersByProjectId(idProject)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateProject/{idProject}")
    public ApiRespon<ProjectRespon> updateProject(
            @PathVariable Integer idProject,
            @RequestBody @Valid ProjectRequest request){

        return ApiRespon.<ProjectRespon>builder()
                .result(projectService.updateProject(idProject, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteProject/{idProject}")
    public ApiRespon<String> deleteProject(@PathVariable Integer idProject){

        projectService.deleteProject(idProject);
        return ApiRespon.<String>builder()
                .result("Project has been deleted").build();
    }


    //Service team
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addTeam")
    public ApiRespon<TeamRespon> addTeam(@RequestBody @Valid TeamRequest request) {

        return ApiRespon.<TeamRespon>builder()
                .result(teamService.createTeam(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllTeam")
    public ApiRespon<List<TeamRespon>> findAllTeam() {

        return ApiRespon.<List<TeamRespon>>builder()
                .result(teamService.findAllTeam()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getTeam/{idTeam}")
    public ApiRespon<TeamRespon> getTeam(@PathVariable Integer idTeam) {

        return ApiRespon.<TeamRespon>builder()
                .result(teamService.getTeam(idTeam)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateTeam/{idTeam}")
    public ApiRespon<TeamRespon> updateTeam(
            @PathVariable Integer idTeam,
            @RequestBody @Valid TeamRequest request){

        return ApiRespon.<TeamRespon>builder()
                .result(teamService.updateTeam(idTeam, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteTeam/{idTeam}")
    public ApiRespon<String> deleteTeam(@PathVariable Integer idTeam){

        teamService.deleteTeam(idTeam);
        return ApiRespon.<String>builder()
                .result("Team has been deleted").build();
    }


    //Service position
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addPosition")
    public ApiRespon<PositionRespon> addPosition(@RequestBody @Valid PositionRequest request) {

        return ApiRespon.<PositionRespon>builder()
                .result(positionService.createPosition(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllPosition")
    public ApiRespon<List<PositionRespon>> findAllPosition() {

        return ApiRespon.<List<PositionRespon>>builder()
                .result(positionService.findAllPosition()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getPosition/{idPosition}")
    public ApiRespon<PositionRespon> getPosition(@PathVariable Integer idPosition) {

        return ApiRespon.<PositionRespon>builder()
                .result(positionService.getPosition(idPosition)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updatePosition/{idPosition}")
    public ApiRespon<PositionRespon> updatePosition(
            @PathVariable Integer idPosition,
            @RequestBody @Valid PositionRequest request){

        return ApiRespon.<PositionRespon>builder()
                .result(positionService.updatePosition(idPosition, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deletePosition/{idPosition}")
    public ApiRespon<String> deletePosition(@PathVariable Integer idPosition){

        positionService.deletePosition(idPosition);
        return ApiRespon.<String>builder()
                .result("Position has been deleted").build();
    }


    //Server status
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addStatus")
    public ApiRespon<StatusRespon> addStatus(@RequestBody @Valid StatusRequest request) {

        return ApiRespon.<StatusRespon>builder()
                .result(statusService.CreateStatus(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllStatus")
    public ApiRespon<List<StatusRespon>> findAllStatus() {

        return ApiRespon.<List<StatusRespon>>builder()
                .result(statusService.findAllStatus()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getStatus/{idStatus}")
    public ApiRespon<StatusRespon> getStatus(@PathVariable Integer idStatus) {

        return ApiRespon.<StatusRespon>builder()
                .result(statusService.getStatus(idStatus)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateStatus/{idStatus}")
    public ApiRespon<StatusRespon> updateStatus(
            @PathVariable Integer idStatus,
            @RequestBody @Valid StatusRequest request){

        return ApiRespon.<StatusRespon>builder()
                .result(statusService.updateStatus(idStatus, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteStatus/{idStatus}")
    public ApiRespon<String> deleteStatus(@PathVariable Integer idStatus){

        statusService.deleteStatus(idStatus);
        return ApiRespon.<String>builder()
                .result("Status has been deleted").build();
    }


    // Service Punishment
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllPunishment")
    public ApiRespon<List<PunishmentRespon>> findAllPunishment() {

        return ApiRespon.<List<PunishmentRespon>>builder()
                .result(punishmentService.findAllPunishment()).build();
    }

//    @PreAuthorize("hasAuthority('Admin')")
//    @PostMapping("/addPunishment")
//    public ApiRespon<PunishmentRespon> addPunishment(@RequestBody @Valid PunishmentRequest request) {
//
//        return ApiRespon.<PunishmentRespon>builder()
//                .result(punishmentService.createPunishment(request)).build();
//    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updatePunishment/{idPunishment}")
    public ApiRespon<PunishmentRespon> updatePunishment(
            @PathVariable Integer idPunishment,
            @RequestBody @Valid PunishmentRequest request){

        return ApiRespon.<PunishmentRespon>builder()
                .result(punishmentService.updatePunishment(idPunishment, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deletePunishment/{idPunishment}")
    public ApiRespon<String> deletePunishment(@PathVariable Integer idPunishment){

        punishmentService.deletePunishment(idPunishment);
        return ApiRespon.<String>builder()
                .result("Punishment has been deleted").build();
    }


    //Service leaveType
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/addLeaveType")
    public ApiRespon<LeaveTypeRespon> addLeaveType(@RequestBody @Valid LeaveTypeRequest request) {

        return ApiRespon.<LeaveTypeRespon>builder()
                .result(leaveTypeService.createLeaveType(request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllLeaveType")
    public ApiRespon<List<LeaveTypeRespon>> findAllLeaveType() {

        return ApiRespon.<List<LeaveTypeRespon>>builder()
                .result(leaveTypeService.findAllLeaveType()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getLeaveType/{idLeaveType}")
    public ApiRespon<LeaveTypeRespon> getLeaveType(@PathVariable Integer idLeaveType) {

        return ApiRespon.<LeaveTypeRespon>builder()
                .result(leaveTypeService.getLeaveType(idLeaveType)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/updateLeaveType/{idLeaveType}")
    public ApiRespon<LeaveTypeRespon> updateLeaveType(
            @PathVariable Integer idLeaveType,
            @RequestBody @Valid LeaveTypeRequest request){

        return ApiRespon.<LeaveTypeRespon>builder()
                .result(leaveTypeService.updateLeaveType(idLeaveType, request)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/deleteLeaveType/{idLeaveType}")
    public ApiRespon<String> deleteLeaveType(@PathVariable Integer idLeaveType){

        leaveTypeService.deleteLeaveType(idLeaveType);
        return ApiRespon.<String>builder()
                .result("LeaveType has been deleted").build();
    }

    //Service Salary
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getAllSalary")
    public ApiRespon<List<SalaryRespon>> findAllSalary() {

        return ApiRespon.<List<SalaryRespon>>builder()
                .result(salaryService.findAllSalary()).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getSalary/{idSalary}")
    public ApiRespon<SalaryRespon> getSalary(@PathVariable Integer idSalary) {

        return ApiRespon.<SalaryRespon>builder()
                .result(salaryService.getSalary(idSalary)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/getSalaryByUser/{username}")
    public ApiRespon<List<SalaryRespon>> getSalaryByUser(@PathVariable String username) {

        return ApiRespon.<List<SalaryRespon>>builder()
                .result(salaryService.getSalaryByUser(username)).build();
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/timekeeping/{time}")
    public ApiRespon<List<SalaryRespon>> timekeeping(@PathVariable YearMonth time) {

        return ApiRespon.<List<SalaryRespon>>builder()
                .result(salaryService.timekeeping(time)).build();
    }

}
