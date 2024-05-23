package lk.ijse.gdse.service;

import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.Exception.NotFoundException;

import java.util.List;


public interface EmployeeService {
    boolean saveEmployee(EmployeeDTO employeeDTO, String password);
    List<EmployeeDTO> getAllEmployees();
    boolean deleteEmployeeById(String email) throws NotFoundException;
    boolean updateEmployeeById(String id, EmployeeDTO employeeDTO, String password) throws NotFoundException;
    EmployeeDTO getEmployeeByEmail(String email) throws NotFoundException;
}
