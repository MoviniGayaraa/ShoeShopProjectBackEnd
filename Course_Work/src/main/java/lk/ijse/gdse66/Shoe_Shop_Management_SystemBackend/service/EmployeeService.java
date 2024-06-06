package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.EmployeeDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;

import java.util.List;

public interface EmployeeService {
    boolean saveEmployee(EmployeeDTO employeeDTO, String password);
    List<EmployeeDTO> getAllEmployees();
    boolean deleteEmployeeById(String email) throws NotFoundException;
    boolean updateEmployeeById(String id, EmployeeDTO employeeDTO, String password) throws NotFoundException;
    EmployeeDTO getEmployeeByEmail(String email) throws NotFoundException;
}
