package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.InventoryDAO;
import lk.ijse.gdse.DAO.SizeDAO;
import lk.ijse.gdse.DAO.SupplierDAO;
import lk.ijse.gdse.DTO.InventoryDTO;
import lk.ijse.gdse.Entity.Inventory;
import lk.ijse.gdse.Entity.Supplier;
import lk.ijse.gdse.Entity.SupplierInventoryDetail;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.conversion.Mapping;
import lk.ijse.gdse.service.InventoryService;
import lk.ijse.gdse.service.SupplierInventoryDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryDAO inventoryDAO;
    private SupplierDAO supplierDAO;
    private SupplierInventoryDetailsService supplierInventoryDetailsService;
    private final Mapping conversionData;
    private final SizeDAO sizeDAO;

    @Override
    public boolean saveInventory(InventoryDTO inventoryDTO, String supplierId) {
        Inventory isSaved = inventoryDAO.save(conversionData.toInventory(inventoryDTO));

        Optional<Supplier> supplier = supplierDAO.findById(supplierId);
        if (supplier.isPresent()) {
            SupplierInventoryDetail supplierInventoryDetail = new SupplierInventoryDetail();
            supplierInventoryDetail.setSupplier_inventory_detail_id(UUID.randomUUID().toString());
            supplierInventoryDetail.setInventory(isSaved);
            supplierInventoryDetail.setSupplier(supplier.get());
            supplierInventoryDetailsService.save(supplierInventoryDetail);
            return true;
        }
        return false;
    }

    @Override
    public List<InventoryDTO> getAllInventories() {
        return conversionData.getInventoryDTOList(inventoryDAO.findAll());
    }

    @Override
    public boolean deleteInventoryById(String id) throws NotFoundException {
        Optional<Inventory> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            inventoryDAO.delete(inventory.get());
            return true;
        }else {
            throw new NotFoundException(id+" not found (:");
        }
    }

    @Override
    public boolean updateInventoryById(String id, InventoryDTO inventoryDTO) throws NotFoundException {
        Optional<Inventory> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            inventory.get().setItem_desc(inventoryDTO.getItem_desc());
            inventory.get().setItem_pic(inventoryDTO.getItem_pic());
            inventory.get().setCategory(inventoryDTO.getCategory());
            return true;
        }else {
            throw new NotFoundException(id+" not found (:");
        }
    }

    public String generateNextId(String occasion, String gender) {
        String occ="";
        switch (occasion) {
            case "Casual": occ = "CS";break;
            case "Formal": occ = "FS";break;
            case "Sport": occ = "SS";break;
            case "Industrial": occ = "IS";break;
        }

        switch (gender) {
            case "MAN": occ = occ + "M";break;
            case "WOMEN": occ = occ + "W";break;
        }

        String lastId = inventoryDAO.findLastId(occ);
        if (lastId == null) {
            return occ + "0001";
        } else {
            int id = Integer.parseInt(lastId.substring(3)) + 1;
            return occ + String.format("%04d", id);
        }
    }

    @Override
    public InventoryDTO selectInventoryById(String id) throws NotFoundException {
        Optional<Inventory> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            return conversionData.toInventoryDTO(inventory.get());
        }else {
            throw new NotFoundException(id+" not found (:");
        }
    }

    @Override
    public boolean updateImg(String itemCode, String pic) {
        Optional<Inventory> inventory = inventoryDAO.findById(itemCode);
        if (inventory.isPresent()) {
            inventory.get().setItem_pic(pic);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<String> getSize(String itemCode) {
        return inventoryDAO.getSize(itemCode);
    }
}
