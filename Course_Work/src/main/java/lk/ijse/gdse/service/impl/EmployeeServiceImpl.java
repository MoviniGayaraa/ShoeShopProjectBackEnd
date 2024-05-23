package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.EmployeeDAO;
import lk.ijse.gdse.DAO.UserDAO;
import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.Entity.Employee;
import lk.ijse.gdse.Entity.User;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.conversion.Mapping;
import lk.ijse.gdse.reqAndresp.secure.SignUp;
import lk.ijse.gdse.service.AuthenticationService;
import lk.ijse.gdse.service.EmployeeService;
import lk.ijse.gdse.util.UtilMatters;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO;
    private final Mapping conversionData;
    private final AuthenticationService authenticationService;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO, String password) {
        Employee employee = conversionData.toEmployee(employeeDTO);
        EmployeeDTO savedEmployee = conversionData.toEmployeeDTO(employeeDAO.save(employee));

        SignUp signUp = new SignUp();
        signUp.setEmail(employeeDTO.getEmail());
        signUp.setPassword(password);
        signUp.setRole(String.valueOf(employeeDTO.getRole()));

        authenticationService.signUp(signUp, employeeDTO);

        return savedEmployee != null;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return conversionData.getEmployeeDTOList(employeeDAO.findAll());
    }

    @Override
    public boolean deleteEmployeeById(String email) throws NotFoundException {
        Optional<Employee> employee = employeeDAO.findByEmail(email);
        Optional<User> user = userDAO.findByEmail(email);
        if (employee.isPresent() && user.isPresent()) {
            userDAO.delete(user.get());
            employeeDAO.delete(employee.get());
            return true;
        }else {
            throw new NotFoundException(email+" not found (:");
        }
    }

    @Override
    public boolean updateEmployeeById(String id, EmployeeDTO employeeDTO, String password) throws NotFoundException {
        Optional<Employee> employee = employeeDAO.findById(id);
        String email = employee.get().getEmail();

        if (employee.isPresent()) {
            employee.get().setEmployeeName(employeeDTO.getEmployeeName());
            employee.get().setEmployeeProfilePic(UtilMatters.convertBase64(employeeDTO.getEmployeeProfilePic()));
            employee.get().setGender(employeeDTO.getGender());
            employee.get().setStatus(employeeDTO.getStatus());
            employee.get().setDesignation(employeeDTO.getDesignation());
            employee.get().setRole(employeeDTO.getRole());
            employee.get().setDob((Date) employeeDTO.getDob());
            employee.get().setJoinDate((Date) employeeDTO.getJoinDate());
            employee.get().setAttachedBranch(employeeDTO.getAttachedBranch());
            employee.get().setEmployeeAddress1(employeeDTO.getEmployeeAddress1());
            employee.get().setEmployeeAddress2(employeeDTO.getEmployeeAddress2());
            employee.get().setEmployeeAddress3(employeeDTO.getEmployeeAddress3());
            employee.get().setEmployeeAddress4(employeeDTO.getEmployeeAddress4());
            employee.get().setEmployeeAddress5(employeeDTO.getEmployeeAddress5());
            employee.get().setContactNo(employeeDTO.getContactNo());
            employee.get().setEmail(employeeDTO.getEmail());
            employee.get().setInformInCaseOfEmergency(employeeDTO.getInformInCaseOfEmergency());
            employee.get().setEmergencyContactNo(employeeDTO.getEmergencyContactNo());

            Optional<User> user = userDAO.findByEmail(email);
            if (user.isPresent()) {
                user.get().setEmail(employeeDTO.getEmail());
                user.get().setPassword(passwordEncoder.encode(password));
                user.get().setRole(employeeDTO.getRole());
            }else{
                throw new NotFoundException(email+" not found (:");
            }
        }
        return false;
    }

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) throws NotFoundException {
        Optional<Employee> employee = employeeDAO.findByEmail(email);

        if (employee.isPresent()) {
            return conversionData.toEmployeeDTO(employee.get());
        }
        throw new NotFoundException(email+" not found (:");
    }
}
