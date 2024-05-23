package lk.ijse.gdse.conversion;

import lk.ijse.gdse.DTO.*;
import lk.ijse.gdse.Entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Mapping {
    final private ModelMapper mapper;

    public Mapping(ModelMapper mapper) {
        this.mapper = mapper;
    }

    //--------------------------------------User--------------------------------------
    public UserDTO toUserDTO(User user) {
        return  mapper.map(user, UserDTO.class);
    }
    public User toUser(UserDTO userDTO) {
        return  mapper.map(userDTO, User.class);
    }

    //--------------------------------------Employee--------------------------------------
    public EmployeeDTO toEmployeeDTO(Employee employee) {
        return  mapper.map(employee, EmployeeDTO.class);
    }

    public Employee toEmployee(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, Employee.class);
    }

    public List<EmployeeDTO> getEmployeeDTOList(List<Employee> employees) {
        return mapper.map(employees, List.class);
    }

    //--------------------------------------Customer--------------------------------------
    public CustomerDTO toCustomerDTO(Customer customer) {
        return  mapper.map(customer, CustomerDTO.class);
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return  mapper.map(customerDTO, Customer.class);
    }

    public List<CustomerDTO> getCustomerDTOList(List<Customer> customers) {
        return mapper.map(customers, List.class);
    }

    //--------------------------------------Supplier--------------------------------------
    public SupplierDTO toSupplierDTO(Supplier supplier) {
        return  mapper.map(supplier, SupplierDTO.class);
    }

    public Supplier toSupplier(SupplierDTO supplierDTO) {
        return  mapper.map(supplierDTO, Supplier.class);
    }

    public List<SupplierDTO> getSupplierDTOList(List<Supplier> suppliers) {
        return mapper.map(suppliers, List.class);
    }

    //--------------------------------------Inventory--------------------------------------
    public InventoryDTO toInventoryDTO(Inventory inventory) {
        return  mapper.map(inventory, InventoryDTO.class);
    }

    public Inventory toInventory(InventoryDTO inventoryDTO) {
        return  mapper.map(inventoryDTO, Inventory.class);
    }

    public List<InventoryDTO> getInventoryDTOList(List<Inventory> inventorys) {
        return mapper.map(inventorys, List.class);
    }

    //--------------------------------------Size--------------------------------------
    public SizeDTO toSizeDTO(Size size) {
        return  mapper.map(size, SizeDTO.class);
    }

    public Size toSize(SizeDTO sizeDTO) {
        return  mapper.map(sizeDTO, Size.class);
    }

    public List<SizeDTO> getSizeDTOList(List<Size> sizes) {
        List<SizeDTO> dtos = new ArrayList<>();
        for (Size size : sizes) {
            SizeDTO sizeDTO = new SizeDTO();
            sizeDTO.setSize(size.getSize());
            sizeDTO.setQuantity(size.getQuantity());
            sizeDTO.setUnit_price_sale(size.getUnit_price_sale());
            sizeDTO.setUnit_price_buy(size.getUnit_price_buy());
            sizeDTO.setExpected_profit(size.getExpected_profit());
            sizeDTO.setProfit_margin(size.getProfit_margin());
            sizeDTO.setItem_code(size.getInventory().getItem_code());
            sizeDTO.setStatus(size.getStatus());
            dtos.add(sizeDTO);
        }
        return dtos;
    }
}
