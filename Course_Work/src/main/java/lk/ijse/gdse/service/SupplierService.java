package lk.ijse.gdse.service;

import lk.ijse.gdse.DTO.SupplierDTO;
import lk.ijse.gdse.Exception.NotFoundException;

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
