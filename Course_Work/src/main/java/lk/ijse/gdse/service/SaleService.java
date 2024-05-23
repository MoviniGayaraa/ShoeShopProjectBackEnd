package lk.ijse.gdse.service;

import lk.ijse.gdse.DTO.PlaceOrderRequestDTO;
import lk.ijse.gdse.Entity.Inventory;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.ProjectionInterface.MostSoldItemProjection;

import java.util.List;


public interface SaleService {
    List<String> getItemIds();
    Inventory getItem(String itemCode);

    boolean placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) throws NotFoundException;
    Double getTotalSale(String date);

    Double getTotalProfit(String date);

    MostSoldItemProjection getMostSaleItem(String date);
}
