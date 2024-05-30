package lk.ijse.gdse.Controller;

import lk.ijse.gdse.DTO.CustomerDTO;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/save")
    public boolean save(@RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomer_id(customerService.genarateNextID());
        return customerService.saveCustomer(customerDTO);
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDTO> findAll() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/update")
    public boolean update(@RequestBody CustomerDTO customerDTO) throws NotFoundException {
        return customerService.updateCustomerById(customerDTO.getCustomer_id(), customerDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestPart("email") String email) throws NotFoundException {
        return customerService.deleteCustomerByEmail(email);
    }

    @GetMapping("/getSelectCustomer")
    public CustomerDTO getSelectCustomer(String email) throws NotFoundException {
        return customerService.getSelectCustomer(email);
    }

    @GetMapping("/getCustomerIds")
    public List<String> getCustomerIds() {
        return customerService.getCustomerIds();
    }

    @GetMapping("/getCustomer/{customer_id}")
    public CustomerDTO getCustomer(@PathVariable String customer_id) throws NotFoundException {
        return customerService.getCustomer(customer_id);
    }
}
