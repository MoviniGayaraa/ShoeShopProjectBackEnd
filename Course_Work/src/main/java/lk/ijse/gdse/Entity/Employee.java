package lk.ijse.gdse.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity
public class Employee implements SuperEntity{
    @Id
    private String employeeId;
    private String employeeName;
    @Column(columnDefinition = "LONGTEXT")
    private String employeeProfilePic;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String status;
    private String designation;
    @Enumerated(EnumType.STRING)
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
}
