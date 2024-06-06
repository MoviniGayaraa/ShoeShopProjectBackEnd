package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.InventoryDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.SizeDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.SupplierDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.InventoryDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.InventoryEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SupplierEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SupplierInventoryDetailEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.mapping.Mapping;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.InventoryService;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.SupplierInventoryDetailsService;
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
        InventoryEntity isSaved = inventoryDAO.save(conversionData.toInventory(inventoryDTO));

        Optional<SupplierEntity> supplier = supplierDAO.findById(supplierId);
        if (supplier.isPresent()) {
            SupplierInventoryDetailEntity supplierInventoryDetail = new SupplierInventoryDetailEntity();
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
        Optional<InventoryEntity> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            inventoryDAO.delete(inventory.get());
            return true;
        }else {
            throw new NotFoundException(id+" not found (:");
        }
    }

    @Override
    public boolean updateInventoryById(String id, InventoryDTO inventoryDTO) throws NotFoundException {
        Optional<InventoryEntity> inventory = inventoryDAO.findById(id);
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
        Optional<InventoryEntity> inventory = inventoryDAO.findById(id);
        if (inventory.isPresent()) {
            return conversionData.toInventoryDTO(inventory.get());
        }else {
            throw new NotFoundException(id+" not found (:");
        }
    }

    @Override
    public boolean updateImg(String itemCode, String pic) {
        Optional<InventoryEntity> inventory = inventoryDAO.findById(itemCode);
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
