package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.controller;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.EmployeeDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.Gender;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.Role;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.EmployeeService;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.utill.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/health")
    public String healthCheck(){
        return "OK";
    }

    @GetMapping(produces = "application/json")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean saveEmployee(
            @RequestPart("employeeName") String employeeName,
            @RequestPart("employeeProfilePic") String employeeProfilePic,
            @RequestPart("gender") String gender,
            @RequestPart("status") String status,
            @RequestPart("designation") String designation,
            @RequestPart("role") String role,
            @RequestPart("dob") String dob,
            @RequestPart("joinDate") String joinDate,
            @RequestPart("attachedBranch") String attachedBranch,
            @RequestPart("employeeAddress1") String employeeAddress1,
            @RequestPart("employeeAddress2") String employeeAddress2,
            @RequestPart("employeeAddress3") String employeeAddress3,
            @RequestPart("employeeAddress4") String employeeAddress4,
            @RequestPart("employeeAddress5") String employeeAddress5,
            @RequestPart("contactNo") String contactNo,
            @RequestPart("email") String email,
            @RequestPart("informInCaseOfEmergency") String informInCaseOfEmergency,
            @RequestPart("emergencyContactNo") String emergencyContactNo,
            @RequestPart("password") String password) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(UUID.randomUUID().toString());
        employeeDTO.setEmployeeName(employeeName);
        employeeDTO.setEmployeeProfilePic(employeeProfilePic);
        employeeDTO.setGender(Gender.valueOf(gender));
        employeeDTO.setStatus(status);
        employeeDTO.setDesignation(designation);
        employeeDTO.setRole(Role.valueOf(role));
        employeeDTO.setDob(dateFormat.parse(dob));
        employeeDTO.setJoinDate(dateFormat.parse(joinDate));
        employeeDTO.setAttachedBranch(attachedBranch);
        employeeDTO.setEmployeeAddress1(employeeAddress1);
        employeeDTO.setEmployeeAddress2(employeeAddress2);
        employeeDTO.setEmployeeAddress3(employeeAddress3);
        employeeDTO.setEmployeeAddress4(employeeAddress4);
        employeeDTO.setEmployeeAddress5(employeeAddress5);
        employeeDTO.setContactNo(contactNo);
        employeeDTO.setEmail(email);
        employeeDTO.setInformInCaseOfEmergency(informInCaseOfEmergency);
        employeeDTO.setEmergencyContactNo(emergencyContactNo);

        return employeeService.saveEmployee(employeeDTO, password);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean updateEmployee(
            @RequestPart("employeeId") String employeeId,
            @RequestPart("employeeName") String employeeName,
            @RequestPart("employeeProfilePic") String employeeProfilePic,
            @RequestPart("gender") String gender,
            @RequestPart("status") String status,
            @RequestPart("designation") String designation,
            @RequestPart("role") String role,
            @RequestPart("dob") String dob,
            @RequestPart("joinDate") String joinDate,
            @RequestPart("attachedBranch") String attachedBranch,
            @RequestPart("employeeAddress1") String employeeAddress1,
            @RequestPart("employeeAddress2") String employeeAddress2,
            @RequestPart("employeeAddress3") String employeeAddress3,
            @RequestPart("employeeAddress4") String employeeAddress4,
            @RequestPart("employeeAddress5") String employeeAddress5,
            @RequestPart("contactNo") String contactNo,
            @RequestPart("email") String email,
            @RequestPart("informInCaseOfEmergency") String informInCaseOfEmergency,
            @RequestPart("emergencyContactNo") String emergencyContactNo,
            @RequestPart("password") String password) throws ParseException, NotFoundException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(UUID.randomUUID().toString());
        employeeDTO.setEmployeeName(employeeName);
        employeeDTO.setEmployeeProfilePic(UtilMatters.convertBase64(employeeProfilePic));
        employeeDTO.setGender(Gender.valueOf(gender));
        employeeDTO.setStatus(status);
        employeeDTO.setDesignation(designation);
        employeeDTO.setRole(Role.valueOf(role));
        employeeDTO.setDob(dateFormat.parse(dob));
        employeeDTO.setJoinDate(dateFormat.parse(joinDate));
        employeeDTO.setAttachedBranch(attachedBranch);
        employeeDTO.setEmployeeAddress1(employeeAddress1);
        employeeDTO.setEmployeeAddress2(employeeAddress2);
        employeeDTO.setEmployeeAddress3(employeeAddress3);
        employeeDTO.setEmployeeAddress4(employeeAddress4);
        employeeDTO.setEmployeeAddress5(employeeAddress5);
        employeeDTO.setContactNo(contactNo);
        employeeDTO.setEmail(email);
        employeeDTO.setInformInCaseOfEmergency(informInCaseOfEmergency);
        employeeDTO.setEmergencyContactNo(emergencyContactNo);

        return employeeService.updateEmployeeById(employeeId, employeeDTO, password);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteEmployee(String email) throws NotFoundException {
        return employeeService.deleteEmployeeById(email);
    }

    @GetMapping("/selectEmployee")
    public EmployeeDTO selectEmployee(String email) throws NotFoundException {
        return employeeService.getEmployeeByEmail(email);
    }
}
