package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.SizeDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SizeEntity;

import java.util.List;

public interface SizeService {
    boolean saveSize(SizeDTO sizeDTO);
    boolean deleteSize(String item_code, String sizeId);
    boolean updateSize(String id, SizeDTO sizeDTO);
    List<SizeDTO> getAllSizes();
    List<String> getItemIds();
    SizeEntity getDataWithSizeAndItemID(String itemCode, String itemSize);
}
