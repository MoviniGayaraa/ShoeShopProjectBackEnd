package lk.ijse.gdse.DTO;

import lk.ijse.gdse.Entity.Gender;
import lk.ijse.gdse.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EmployeeDTO {
    private String employeeId;
    private String employeeName;
    private String employeeProfilePic;
    private Gender gender;
    private String status;
    private String designation;
    private Role role;
    private Date dob;
    private Date joinDate;
    private String attachedBranch;
    private String employeeAddress1;
    private String employeeAddress2;
    private String employeeAddress3;
    private String employeeAddress4;
    private String employeeAddress5;
    private String contactNo;
    private String email;
    private String informInCaseOfEmergency;
    private String emergencyContactNo;
    private String password;
}


/*
{
        "employeeName":"Amil",
        "employeeProfilePic":"",
        "gender":"",
        "status":"",
        "role":"",
        "dob":"",
        "joinDate":"",
        "attachedBranch":"",
        "employeeAddress1":"",
        "employeeAddress2":"",
        "employeeAddress3":"",
        "employeeAddress4":"",
        "employeeAddress5":"",
        "contactNo":"",
        "email":"",
        "informInCaseOfEmergency":"",
        "emergencyContactNo":""
}*/
