package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao.*;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.OrderItemDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dto.PlaceOrderRequestDTO;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.*;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.exception.NotFoundException;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.projectionInterface.MostSoldItemProjection;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class SaleServiceImpl implements SaleService {
    private InventoryDAO inventoryDAO;
    private SaleDAO saleRepository;
    private SaleInventoryDetailDAO saleInventoryDetailRepository;
    private UserDAO userDAO;
    private CustomerDAO customerDAO;

    @Override
    public List<String> getItemIds() {
        return inventoryDAO.getItemIds();
    }

    @Override
    public InventoryEntity getItem(String itemCode) {
        Optional<InventoryEntity> item = inventoryDAO.findById(itemCode);
        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    @Override
    public boolean placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) throws NotFoundException {
        Optional<UserEntity> user = userDAO.findByEmail(placeOrderRequestDTO.getUserEmail());
        Optional<CustomerEntity> customer = customerDAO.findById(placeOrderRequestDTO.getCustomer_id());

        if (!user.isPresent()) {
            return false;
        }

        System.out.println(">>>>>>>>>>>>>>>> : "+user.get().getEmployee().getEmployeeName());

        SaleEntity sale = new SaleEntity();
        sale.setOrder_id(UUID.randomUUID().toString());
        sale.setUser(user.get());
        sale.setCashier_name(user.get().getEmployee().getEmployeeName());

        if (customer.isPresent()) {
            sale.setCustomer(customer.get());

            if (placeOrderRequestDTO.getNet_total() > 800){
                sale.setAdded_points(1);
                customer.get().setTotal_points(customer.get().getTotal_points() + 1);

                if (customer.get().getTotal_points() < 50) {
                    customer.get().setLevel(Level.NEW);
                }

                if (customer.get().getTotal_points() >= 50 & customer.get().getTotal_points() <= 99) {
                    customer.get().setLevel(Level.BRONZE);
                }

                if (customer.get().getTotal_points() >= 100 & customer.get().getTotal_points() <= 199) {
                    customer.get().setLevel(Level.SILVER);
                }

                if (customer.get().getTotal_points() >= 200 & customer.get().getTotal_points() <= 299) {
                    customer.get().setLevel(Level.GOLD);
                }
            }
        } else {
            sale.setCustomer(null);
        }

        sale.setCustomer_name(placeOrderRequestDTO.getCustomer_name());
        sale.setTotal_price(placeOrderRequestDTO.getNet_total());
        sale.setPurchase_date(new Date());
        sale.setPayment_method(placeOrderRequestDTO.getPayment_type());

        SaleEntity savedSale = saleRepository.save(sale);

        List<SaleInventoryDetailEntity> saleInventoryDetails = new ArrayList<>();
        for (OrderItemDTO item : placeOrderRequestDTO.getItems()) {
            InventoryEntity inventory = inventoryDAO.findById(item.getItem_id())
                    .orElseThrow(() -> new NotFoundException("Item not found"));

            SaleInventoryDetailEntity saleInventoryDetail = new SaleInventoryDetailEntity();
            saleInventoryDetail.setOrderDetailID(UUID.randomUUID().toString());
            saleInventoryDetail.setInventory(inventory);
            saleInventoryDetail.setSale(savedSale);
            saleInventoryDetail.setItemName(item.getItem_name());
            saleInventoryDetail.setQuantity(item.getQuantity());
            saleInventoryDetail.setSize(item.getItem_size());
            saleInventoryDetail.setUnitPrice(item.getUnit_price());
            saleInventoryDetail.setSubTotal(item.getTotal_price());

            saleInventoryDetails.add(saleInventoryDetail);
        }
        saleInventoryDetailRepository.saveAll(saleInventoryDetails);

        for (SaleInventoryDetailEntity saleInventoryDetail : saleInventoryDetails) {
            InventoryEntity inventory = saleInventoryDetail.getInventory();
            int soldQuantity = saleInventoryDetail.getQuantity();
            int sizeToReduce = saleInventoryDetail.getSize();

            for (SizeEntity size : inventory.getSizes()) {
                if (size.getSize() == sizeToReduce) {
                    int currentQuantity = size.getQuantity();
                    size.setQuantity(currentQuantity - soldQuantity);
                    break;
                }
            }
        }

        inventoryDAO.saveAll(saleInventoryDetails.stream()
                .map(SaleInventoryDetailEntity::getInventory)
                .collect(Collectors.toSet()));


        return true;
    }

    @Override
    public Double getTotalSale(String date) {
        return saleRepository.getTotalSale(date);
    }

    @Override
    public Double getTotalProfit(String date) {
        return saleRepository.getTotalProfit(date);
    }

    @Override
    public MostSoldItemProjection getMostSaleItem(String date) {
        return saleRepository.getMostSaleItem(date);
    }
}
