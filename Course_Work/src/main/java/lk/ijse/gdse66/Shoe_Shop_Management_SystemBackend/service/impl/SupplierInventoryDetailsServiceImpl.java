package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.SaleInventoryDetailDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.SupplierInventoryDetailDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SupplierInventoryDetailEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.SupplierInventoryDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class SupplierInventoryDetailsServiceImpl implements SupplierInventoryDetailsService {
    private SupplierInventoryDetailDAO supplierInventoryDetailDAO;

    @Override
    public boolean save(SupplierInventoryDetailEntity supplierInventoryDetail) {
        SupplierInventoryDetailEntity isSaved = supplierInventoryDetailDAO.save(supplierInventoryDetail);
        return isSaved != null;
    }
}
