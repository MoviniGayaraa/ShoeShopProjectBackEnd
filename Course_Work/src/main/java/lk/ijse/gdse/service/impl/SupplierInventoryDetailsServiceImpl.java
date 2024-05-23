package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.SupplierInventoryDetailDAO;
import lk.ijse.gdse.Entity.SupplierInventoryDetail;
import lk.ijse.gdse.service.SupplierInventoryDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Transactional
@AllArgsConstructor
public class SupplierInventoryDetailsServiceImpl implements SupplierInventoryDetailsService {
    private SupplierInventoryDetailDAO supplierInventoryDetailDAO;

    @Override
    public boolean save(SupplierInventoryDetail supplierInventoryDetail) {
        SupplierInventoryDetail isSaved = supplierInventoryDetailDAO.save(supplierInventoryDetail);
        return isSaved != null;
    }
}
