package lk.ijse.gdse.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.DAO.*;
import lk.ijse.gdse.DTO.OrderItemDTO;
import lk.ijse.gdse.DTO.PlaceOrderRequestDTO;
import lk.ijse.gdse.Entity.*;
import lk.ijse.gdse.Exception.NotFoundException;
import lk.ijse.gdse.ProjectionInterface.MostSoldItemProjection;
import lk.ijse.gdse.service.SaleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Inventory getItem(String itemCode) {
        Optional<Inventory> item = inventoryDAO.findById(itemCode);
        if (item.isPresent()) {
            return item.get();
        }
        return null;
    }

    @Override
    public boolean placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) throws NotFoundException {
        Optional<User> user = userDAO.findByEmail(placeOrderRequestDTO.getUserEmail());
        Optional<Customer> customer = customerDAO.findById(placeOrderRequestDTO.getCustomer_id());

        if (!user.isPresent()) {
            return false;
        }

        Sale sale = new Sale();
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

        Sale savedSale = saleRepository.save(sale);

        List<SaleInventoryDetail> saleInventoryDetails = new ArrayList<>();
        for (OrderItemDTO item : placeOrderRequestDTO.getItems()) {
            Inventory inventory = inventoryDAO.findById(item.getItem_id())
                    .orElseThrow(() -> new NotFoundException("Item not found"));

            SaleInventoryDetail saleInventoryDetail = new SaleInventoryDetail();
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

        for (SaleInventoryDetail saleInventoryDetail : saleInventoryDetails) {
            Inventory inventory = saleInventoryDetail.getInventory();
            int soldQuantity = saleInventoryDetail.getQuantity();
            int sizeToReduce = saleInventoryDetail.getSize();

            for (Size size : inventory.getSizes()) {
                if (size.getSize() == sizeToReduce) {
                    int currentQuantity = size.getQuantity();
                    size.setQuantity(currentQuantity - soldQuantity);
                    break;
                }
            }
        }

        inventoryDAO.saveAll(saleInventoryDetails.stream()
                .map(SaleInventoryDetail::getInventory)
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
