package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.SupplierDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;

import java.util.List;

public interface SupplierService {
    boolean saveSupplier(SupplierDTO supplierDTO);
    List<SupplierDTO> getAllSuppliers();
    boolean deleteSupplierByEmail(String email) throws NotFoundException;
    boolean updateSupplierById(String id, SupplierDTO supplierDTO) throws NotFoundException;
    SupplierDTO selectSupplierByEmail(String email) throws NotFoundException;
    String generateNextId();
    List<String> getSupplierIds();
}
