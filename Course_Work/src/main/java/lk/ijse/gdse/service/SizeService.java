package lk.ijse.gdse.service;

import lk.ijse.gdse.DTO.SizeDTO;
import lk.ijse.gdse.Entity.Size;

import java.util.List;


public interface SizeService {
    boolean saveSize(SizeDTO sizeDTO);
    boolean deleteSize(String item_code, String sizeId);
    boolean updateSize(String id, SizeDTO sizeDTO);
    List<SizeDTO> getAllSizes();
    List<String> getItemIds();
    Size getDataWithSizeAndItemID(String itemCode, String itemSize);
}
