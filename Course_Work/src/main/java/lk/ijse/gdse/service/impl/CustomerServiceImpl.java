package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.CustomerDAO;
import lk.ijse.gdse.DTO.CustomerDTO;
import lk.ijse.gdse.Entity.Customer;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.conversion.Mapping;
import lk.ijse.gdse.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO;
    private final Mapping conversionData;

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customer = customerDAO.findByEmail(customerDTO.getEmail());
        if (customer.isPresent()) {
            return false;
        }else {
            customerDAO.save(conversionData.toCustomer(customerDTO));
            return true;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return conversionData.getCustomerDTOList(customerDAO.findAll());
    }

    @Override
    public boolean deleteCustomerByEmail(String email) throws NotFoundException {
        Optional<Customer> customer = customerDAO.findByEmail(email);
        if (customer.isPresent()) {
            customerDAO.delete(customer.get());
            return true;
        }else{
            throw new NotFoundException(email+" not found (:");
        }
    }

    @Override
    public boolean updateCustomerById(String id, CustomerDTO customerDTO) throws NotFoundException {
        Optional<Customer> customer = customerDAO.findById(id);
        if (customer.isPresent()) {
            customer.get().setName(customerDTO.getName());
            customer.get().setGender(customerDTO.getGender());
            customer.get().setJoin_date_as_a_loyalty_customer(customerDTO.getJoin_date_as_a_loyalty_customer());
            customer.get().setLevel(customerDTO.getLevel());
            customer.get().setTotal_points(customerDTO.getTotal_points());
            customer.get().setDob(customerDTO.getDob());
            customer.get().setAddress_line_1(customerDTO.getAddress_line_1());
            customer.get().setAddress_line_2(customerDTO.getAddress_line_2());
            customer.get().setAddress_line_3(customerDTO.getAddress_line_3());
            customer.get().setAddress_line_4(customerDTO.getAddress_line_4());
            customer.get().setAddress_line_5(customerDTO.getAddress_line_5());
            customer.get().setContact_no(customerDTO.getContact_no());
            customer.get().setEmail(customerDTO.getEmail());
            customer.get().setRecent_purchase_date_and_time(customerDTO.getRecent_purchase_date_and_time());
            return true;
        }else{
            throw new NotFoundException(id+" not found (:");
        }
    }

    @Override
    public CustomerDTO getSelectCustomer(String email) throws NotFoundException {
        Optional<Customer> customer = customerDAO.findByEmail(email);
        if (customer.isPresent()) {
            return conversionData.toCustomerDTO(customer.get());
        }else{
            throw new NotFoundException(email+" not found (:");
        }
    }

    @Override
    public List<String> getCustomerIds() {
        return customerDAO.getCustomerIds();
    }

    @Override
    public String genarateNextID() {
        if (customerDAO.findLastId() == null) {
            return "C0001";
        }
        String numericPart = customerDAO.findLastId().substring(1);
        int lastNumericValue = Integer.parseInt(numericPart);
        int nextNumericValue = lastNumericValue + 1;
        String nextId = "C" + String.format("%04d", nextNumericValue);
        return nextId;
    }

    @Override
    public CustomerDTO getCustomer(String customerId) {
        Optional<Customer> customer = customerDAO.findById(customerId);
        if (customer.isPresent()) {
            return conversionData.toCustomerDTO(customer.get());
        }
        return null;
    }
}
