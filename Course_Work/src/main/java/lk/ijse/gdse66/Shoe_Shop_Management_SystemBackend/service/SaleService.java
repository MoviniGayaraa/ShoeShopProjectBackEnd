package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.PlaceOrderRequestDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.InventoryEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.projectionInterface.MostSoldItemProjection;

import java.util.List;

public interface SaleService {
    List<String> getItemIds();
    InventoryEntity getItem(String itemCode);

    boolean placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) throws NotFoundException;
    Double getTotalSale(String date);

    Double getTotalProfit(String date);

    MostSoldItemProjection getMostSaleItem(String date);
}
