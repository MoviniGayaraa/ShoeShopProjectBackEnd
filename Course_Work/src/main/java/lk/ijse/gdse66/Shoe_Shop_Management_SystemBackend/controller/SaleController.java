package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.controller;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.PlaceOrderRequestDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.InventoryEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.projectionInterface.MostSoldItemProjection;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.InventoryService;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SaleController {
    private final SaleService saleService;
    private final InventoryService inventoryService;

    @GetMapping("/health")
    public String health(){
        return "OK";
    }

    @GetMapping("/getItemIds")
    public List<String> getItemIds(){
        return saleService.getItemIds();
    }

    @GetMapping("getItem/{itemCode}")
    public InventoryEntity getItem(@PathVariable String itemCode){
        return saleService.getItem(itemCode);
    }

    @GetMapping("getItemSize/{itemCode}")
    public List<String> getSize(@PathVariable String itemCode){
        return inventoryService.getSize(itemCode);
    }

    @PostMapping("/placeOrder")
    public boolean placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) throws NotFoundException {
        System.out.println(placeOrderRequestDTO);
        return saleService.placeOrder(placeOrderRequestDTO);
    }

    @GetMapping("/getTotalSale/{date}")
    public Double getTotalSale(@PathVariable String date){
        return saleService.getTotalSale(date);
    }

    @GetMapping("/getTotalProfit/{date}")
    public Double getTotalProfit(@PathVariable String date){
        return saleService.getTotalProfit(date);
    }

    @GetMapping("/getMostSaleItem/{date}")
    public MostSoldItemProjection getMostSaleItem(@PathVariable String date){
        return saleService.getMostSaleItem(date);
    }
}
