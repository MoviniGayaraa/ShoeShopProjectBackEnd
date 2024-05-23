package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.SupplierDAO;
import lk.ijse.gdse.DTO.SupplierDTO;
import lk.ijse.gdse.Entity.Supplier;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.conversion.Mapping;
import lk.ijse.gdse.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private SupplierDAO supplierDAO;
    private final Mapping conversionData;

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) {
        Supplier isSaved = supplierDAO.save(conversionData.toSupplier(supplierDTO));
        return isSaved != null;
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return conversionData.getSupplierDTOList(supplierDAO.findAll());
    }

    @Override
    public boolean deleteSupplierByEmail(String email) throws NotFoundException {
        Optional<Supplier> supplier = supplierDAO.findByEmail(email);
        if (supplier.isPresent()) {
            supplierDAO.delete(supplier.get());
            return true;
        }else{
            throw new NotFoundException(email+" not found (:");
        }
    }

    @Override
    public boolean updateSupplierById(String id, SupplierDTO supplierDTO) throws NotFoundException {
        Optional<Supplier> supplier = supplierDAO.findById(id);
        if (supplier.isPresent()) {
            supplierDAO.save(conversionData.toSupplier(supplierDTO));
            return true;
        }else{
            throw new NotFoundException(id+" not found (:");
        }
    }

    @Override
    public SupplierDTO selectSupplierByEmail(String email) throws NotFoundException {
        Optional<Supplier> supplier = supplierDAO.findByEmail(email);
        if (supplier.isPresent()) {
            return conversionData.toSupplierDTO(supplier.get());
        }else{
            throw new NotFoundException(email+" not found (:");
        }
    }

    public String generateNextId() {
        if (supplierDAO.findLastId() == null) {
            return "S0001";
        }
        String numericPart = supplierDAO.findLastId().substring(1);
        int lastNumericValue = Integer.parseInt(numericPart);
        int nextNumericValue = lastNumericValue + 1;
        String nextId = "S" + String.format("%04d", nextNumericValue);
        return nextId;
    }

    @Override
    public List<String> getSupplierIds() {
        return supplierDAO.getSupplierIds();
    }
}
