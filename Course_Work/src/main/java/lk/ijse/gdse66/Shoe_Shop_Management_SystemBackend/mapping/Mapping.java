package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.mapping;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.*;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.*;
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
    public UserDTO toUserDTO(UserEntity user) {
        return  mapper.map(user, UserDTO.class);
    }
    public UserEntity toUser(UserDTO userDTO) {
        return  mapper.map(userDTO, UserEntity.class);
    }

    //--------------------------------------Employee--------------------------------------
    public EmployeeDTO toEmployeeDTO(EmployeeEntity employee) {
        return  mapper.map(employee, EmployeeDTO.class);
    }

    public EmployeeEntity toEmployee(EmployeeDTO employeeDTO) {
        return  mapper.map(employeeDTO, EmployeeEntity.class);
    }

    public List<EmployeeDTO> getEmployeeDTOList(List<EmployeeEntity> employees) {
        return mapper.map(employees, List.class);
    }

    //--------------------------------------Customer--------------------------------------
    public CustomerDTO toCustomerDTO(CustomerEntity customer) {
        return  mapper.map(customer, CustomerDTO.class);
    }

    public CustomerEntity toCustomer(CustomerDTO customerDTO) {
        return  mapper.map(customerDTO, CustomerEntity.class);
    }

    public List<CustomerDTO> getCustomerDTOList(List<CustomerEntity> customers) {
        return mapper.map(customers, List.class);
    }

    //--------------------------------------Supplier--------------------------------------
    public SupplierDTO toSupplierDTO(SupplierEntity supplier) {
        return  mapper.map(supplier, SupplierDTO.class);
    }

    public SupplierEntity toSupplier(SupplierDTO supplierDTO) {
        return  mapper.map(supplierDTO, SupplierEntity.class);
    }

    public List<SupplierDTO> getSupplierDTOList(List<SupplierEntity> suppliers) {
        return mapper.map(suppliers, List.class);
    }

    //--------------------------------------Inventory--------------------------------------
    public InventoryDTO toInventoryDTO(InventoryEntity inventory) {
        return  mapper.map(inventory, InventoryDTO.class);
    }

    public InventoryEntity toInventory(InventoryDTO inventoryDTO) {
        return  mapper.map(inventoryDTO, InventoryEntity.class);
    }

    public List<InventoryDTO> getInventoryDTOList(List<InventoryEntity> inventorys) {
        return mapper.map(inventorys, List.class);
    }

    //--------------------------------------Size--------------------------------------
    public SizeDTO toSizeDTO(SizeEntity size) {
        return  mapper.map(size, SizeDTO.class);
    }

    public SizeEntity toSize(SizeDTO sizeDTO) {
        return  mapper.map(sizeDTO, SizeEntity.class);
    }

    public List<SizeDTO> getSizeDTOList(List<SizeEntity> sizes) {
        List<SizeDTO> dtos = new ArrayList<>();
        for (SizeEntity size : sizes) {
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
