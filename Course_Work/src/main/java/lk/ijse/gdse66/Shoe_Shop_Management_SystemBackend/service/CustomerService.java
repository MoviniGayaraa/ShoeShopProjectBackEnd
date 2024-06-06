package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.CustomerDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;

import java.util.List;

public interface CustomerService {
    boolean saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomers();
    boolean deleteCustomerByEmail(String id) throws NotFoundException;
    boolean updateCustomerById(String id, CustomerDTO CustomerDTO) throws NotFoundException;
    CustomerDTO getSelectCustomer(String email) throws NotFoundException;
    List<String> getCustomerIds();
    String genarateNextID();
    CustomerDTO getCustomer(String customerId);
}
