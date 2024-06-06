package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.dao;

import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.entity.SaleEntity;
import lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.projectionInterface.MostSoldItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleDAO extends JpaRepository<SaleEntity, String> {
    @Query(value = "SELECT item_code FROM inventory", nativeQuery = true)
    List<String> getItemIds();

    @Query(value = "SELECT SUM(total_price) FROM sale WHERE purchase_date LIKE :date%", nativeQuery = true)
    Double getTotalSale(String date);



    @Query(value = "SELECT\n" +
            "    SUM((sid.quantity * (sid.unitPrice - s.size))) AS total_profit\n" +
            "FROM\n" +
            "    size s\n" +
            "JOIN\n" +
            "    sale_inventory_details sid ON s.size = sid.size AND s.inventory_item_code = sid.item_code\n" +
            "JOIN\n" +
            "    sale sa ON sid.order_id = sa.order_id\n" +
            "WHERE\n" +
            "    DATE(sa.purchase_date) LIKE :date%", nativeQuery = true)
    Double getTotalProfit(String date);



    @Query(value = "SELECT\n" +
            "    sid.item_code,\n" +
            "    sid.size,\n" +
            "    SUM(sid.quantity) AS total_quantity_sold,\n" +
            "    i.item_pic,\n" +
            "    i.item_desc\n" +
            "FROM\n" +
            "    sale_inventory_details sid\n" +
            "JOIN\n" +
            "    sale s ON sid.order_id = s.order_id\n" +
            "JOIN\n" +
            "    helloshoes.inventory i ON sid.item_code = i.item_code\n" +
            "WHERE\n" +
            "    DATE(s.purchase_date) = :date\n" +
            "GROUP BY\n" +
            "    sid.item_code, sid.size, i.item_pic -- or i.picture\n" +
            "ORDER BY\n" +
            "    total_quantity_sold DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    MostSoldItemProjection getMostSaleItem(String date);
}
