package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.InventoryDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.SizeDAO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.SizeDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.InventoryEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SizeEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.mapping.Mapping;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {
    private SizeDAO sizeDAO;
    private final Mapping conversionData;
    private InventoryDAO inventoryDAO;

    @Override
    public boolean saveSize(SizeDTO sizeDTO) {
        Optional<InventoryEntity> inventoryOptional = inventoryDAO.findById(sizeDTO.getItem_code());

        if(isExitIDs(String.valueOf(sizeDTO.getSize()), sizeDTO.getItem_code())){
            if (inventoryOptional.isPresent()) {
                InventoryEntity inventory = inventoryOptional.get();
                SizeEntity size = conversionData.toSize(sizeDTO);
                size.setInventory(inventory);
                try {
                    sizeDAO.save(size);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isExitIDs(String sizeId, String itemCode) {
        if (sizeDAO.countBySizeIdAndItemCode(sizeId, itemCode) !=0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean deleteSize(String itemCode, String sizeId) {
        int isDeleted = sizeDAO.deleteByItemCodeAndSizeId(itemCode, sizeId);

        if (isDeleted != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSize(String id, SizeDTO sizeDTO) {
        return false;
    }

    @Override
    public List<SizeDTO> getAllSizes() {
        return conversionData.getSizeDTOList(sizeDAO.findAll());
    }

    @Override
    public List<String> getItemIds() {
        return inventoryDAO.getItemIds();
    }

    @Override
    public SizeEntity getDataWithSizeAndItemID(String itemCode, String itemSize) {
        return sizeDAO.getDataWithSizeAndItemID(itemCode,itemSize);
    }
}
