package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.controller;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.InventoryDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.InventoryGender;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.InventoryService;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.utill.UtilMatters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/health")
    public String health(){
        return "OK";
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean save(
            @RequestPart("item_desc") String item_desc,
            @RequestPart("item_pic") String item_pic,
            @RequestPart("category") String category,
            @RequestPart("status") String status,
            @RequestPart("gender") String gender,
            @RequestPart("occasion") String occasion,
            @RequestPart("supplier_id") String supplier_id
    ) throws NotFoundException {

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItem_code(inventoryService.generateNextId(occasion,gender));
        inventoryDTO.setItem_desc(item_desc);
        inventoryDTO.setItem_pic(item_pic);
        inventoryDTO.setCategory(category);
        inventoryDTO.setGender(InventoryGender.valueOf(gender));
        inventoryDTO.setOccasion(occasion);

        return inventoryService.saveInventory(inventoryDTO, supplier_id);
    }

    @GetMapping
    public List<InventoryDTO> getAllInventory(){
        return inventoryService.getAllInventories();
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean updateInventory(
            @RequestPart("item_code") String item_code,
            @RequestPart("item_desc") String item_desc,
            @RequestPart("item_qty") String item_qty,
            @RequestPart("item_pic") String item_pic,
            @RequestPart("category") String category,
            @RequestPart("size") String size,
            @RequestPart("unit_price_sale") String unit_price_sale,
            @RequestPart("unit_price_buy") String unit_price_buy,
            @RequestPart("expected_profit") String expected_profit,
            @RequestPart("profit_margin") String profit_margin,
            @RequestPart("status") String status
    ) throws NotFoundException {

        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItem_code(item_code);
        inventoryDTO.setItem_desc(item_desc);
        inventoryDTO.setItem_pic(UtilMatters.convertBase64(item_pic));
        inventoryDTO.setCategory(category);
        inventoryDTO.setItem_qty(Integer.parseInt(item_qty));
        inventoryDTO.setUnit_price_sale(Double.parseDouble(unit_price_sale));
        inventoryDTO.setUnit_price_buy(Double.parseDouble(unit_price_buy));
        inventoryDTO.setExpected_profit(Double.parseDouble(expected_profit));
        inventoryDTO.setProfit_margin(Double.parseDouble(profit_margin));

        return inventoryService.updateInventoryById(item_code, inventoryDTO);
    }

    @DeleteMapping("/delete")
    public boolean deleteInventory(String item_code) throws NotFoundException {
        return inventoryService.deleteInventoryById(item_code);
    }

    @GetMapping("/selectInventory")
    public InventoryDTO selectInventory(String item_code) throws NotFoundException {
        return inventoryService.selectInventoryById(item_code);
    }

    @PostMapping("/updateImg")
    public boolean updateImg(
            @RequestPart("item_code") String item_code,
            @RequestPart("item_pic") String item_pic
    ) throws NotFoundException {
        return inventoryService.updateImg(item_code, UtilMatters.convertBase64(item_pic));
    }
}
